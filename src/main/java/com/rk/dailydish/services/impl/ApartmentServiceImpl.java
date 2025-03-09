package com.rk.dailydish.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.dailydish.dto.ApartmentDto;
import com.rk.dailydish.entity.Apartment;
import com.rk.dailydish.exceptions.InternalServerException;
import com.rk.dailydish.repository.ApartmentRepo;
import com.rk.dailydish.services.ApartmentService;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private ApartmentRepo apartmentRepo;
	
	@Override
	public ApartmentDto saveApartment(ApartmentDto apartmentDto) {
		// TODO Auto-generated method stub
		try {
			Apartment newApartment =  this.dtoToApartment(apartmentDto);
			newApartment.setId(apartmentDto.getId());
			newApartment.setAddress(apartmentDto.getAddress());
			newApartment.setName(apartmentDto.getName());
			newApartment.setCreatedAt(LocalDateTime.now());
			newApartment.setPincode(apartmentDto.getPincode());
			newApartment.setGateDeliveryPrice(apartmentDto.getGateDeliveryPrice());
			newApartment.setTowerDeliveryPrice(apartmentDto.getTowerDeliveryPrice());
			newApartment.setApproxDeliveryTime(apartmentDto.getApproxDeliveryTime());
			
			Apartment savedApartment = this.apartmentRepo.save(newApartment);

			ApartmentDto newapartmentDto = this.apartmentToDto(savedApartment);
			
			return newapartmentDto;
		}
		catch(Exception e) {
			throw new InternalServerException("Intrnal Server Error");
		}
	
	}

	private ApartmentDto apartmentToDto(Apartment apartment) {
	ApartmentDto newapartment =	modelmapper.map(apartment, ApartmentDto.class);
	return newapartment;
	}
	
	private Apartment dtoToApartment(ApartmentDto apartmDto) {
		Apartment apartment = modelmapper.map(apartmDto, Apartment.class);
		return apartment;
	}
}
