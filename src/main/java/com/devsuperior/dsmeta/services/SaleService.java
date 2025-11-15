package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;


	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


    public Page<SaleMinDTO> findSeller(String name, String minDateStr, String maxDateStr, Pageable pageable) {
        LocalDate[] range = DateUtil.parseMinAndMaxDate(minDateStr, maxDateStr);
        LocalDate minDate = range[0];
        LocalDate maxDate = range[1];

        Page<Sale> result = repository.findBySeller(name, minDate, maxDate, pageable);
        return result.map(x -> new SaleMinDTO(x));
    }

    public Page<SaleSumDTO> findSellerName(String minDateStr, String maxDateStr, Pageable pageable) {
        LocalDate[] range = DateUtil.parseMinAndMaxDate(minDateStr, maxDateStr);
        LocalDate minDate = range[0];
        LocalDate maxDate = range[1];

       return  repository.findBySellerName(minDate, maxDate, pageable);

    }
}
