package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
            @RequestParam(name= "name", defaultValue = "") String name,
            @RequestParam(name = "minDate", required = false) String minDate,
            @RequestParam(name = "maxDate",required = false) String maxDate,
            Pageable pageable) {

        Page<SaleMinDTO> dto = service.findSeller(name, minDate, maxDate, pageable);
        return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumDTO>> getSummary(
            @RequestParam(name = "minDate", required = false) String minDate,
            @RequestParam(name = "maxDate",required = false) String maxDate,
            Pageable pageable
    ) {
        Page<SaleSumDTO> dto = service.findSellerName(minDate, maxDate, pageable);
        return ResponseEntity.ok(dto);
	}
}
