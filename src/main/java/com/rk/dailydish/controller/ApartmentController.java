package com.rk.dailydish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dailydish.dto.ApartmentDto;
import com.rk.dailydish.dto.ApartmentUpdateDto;
import com.rk.dailydish.services.ApartmentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
	
	@Autowired
	private ApartmentService apartmentService;

	@PostMapping("/")
	public ResponseEntity<ApartmentDto> saveApartment(@Valid @RequestBody ApartmentDto apartmentDto) {
		//TODO: process POST request
		ApartmentDto saveApartment = this.apartmentService.saveApartment(apartmentDto);
		
		if(saveApartment==null) {
			return new ResponseEntity<ApartmentDto>(saveApartment,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ApartmentDto>(saveApartment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApartmentDto> deleteApartment( @Valid @PathVariable int id){
		
		ApartmentDto deleteApartment = this.apartmentService.deleteApartment(id);
		
		return new ResponseEntity<ApartmentDto>(deleteApartment,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ApartmentDto>> getAllApartments(){
		
		List<ApartmentDto> allApartment = this.apartmentService.allApartment();
		return new ResponseEntity<List<ApartmentDto>>(allApartment,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApartmentDto> getAllApartments(@Valid @PathVariable int id){
		
		 ApartmentDto apartment = this.apartmentService.getApartment(id);
		return new ResponseEntity<ApartmentDto>(apartment,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ApartmentUpdateDto> getApartment(@Valid @RequestBody ApartmentUpdateDto apartment,@PathVariable int id){
		
		ApartmentUpdateDto updateApartment = this.apartmentService.updateApartment(apartment, id);
		
		return new ResponseEntity<ApartmentUpdateDto>(updateApartment,HttpStatus.OK);
	}
	
	
}
