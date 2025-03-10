package com.rk.dailydish.services;

import java.util.List;

import com.rk.dailydish.dto.ApartmentDto;
import com.rk.dailydish.dto.ApartmentUpdateDto;

public interface ApartmentService {

	ApartmentDto saveApartment(ApartmentDto apartment);
	
	ApartmentDto deleteApartment(int id);
	
	List<ApartmentDto> allApartment();
	
	ApartmentDto getApartment(int id);
	
	ApartmentUpdateDto updateApartment(ApartmentUpdateDto apartment,int id);
}
