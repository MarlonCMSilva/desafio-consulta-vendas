package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj FROM Sale obj "
            + "WHERE (:name = ''  OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))) "
            + "AND obj.date BETWEEN :minDate AND :maxDate")
    Page<Sale> findBySeller(
           @Param("name") String name,
           @Param("minDate") LocalDate minDate,
           @Param("maxDate") LocalDate maxDate,
           Pageable pageable
    );

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSumDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "GROUP BY obj.seller.name"
    )
    Page<SaleSumDTO> findBySellerName(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            Pageable pageable
    );


}
