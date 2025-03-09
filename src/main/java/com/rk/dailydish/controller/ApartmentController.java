package com.rk.dailydish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dailydish.dto.ApartmentDto;
import com.rk.dailydish.services.ApartmentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
	
	@Autowired
	private ApartmentService apartmentService;

	@PostMapping("/saveApartment")
	public ResponseEntity<ApartmentDto> saveApartment(@Valid @RequestBody ApartmentDto apartmentDto) {
		//TODO: process POST request
		ApartmentDto saveApartment = this.apartmentService.saveApartment(apartmentDto);
		
		if(saveApartment==null) {
			return new ResponseEntity<ApartmentDto>(saveApartment,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApartmentDto>(saveApartment,HttpStatus.CREATED);
	}
	
	
}
