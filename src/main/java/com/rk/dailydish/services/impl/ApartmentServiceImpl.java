package com.rk.dailydish.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.dailydish.dto.ApartmentDto;
import com.rk.dailydish.dto.ApartmentUpdateDto;
import com.rk.dailydish.entity.Apartment;
import com.rk.dailydish.exceptions.InternalServerException;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.repository.ApartmentRepo;
import com.rk.dailydish.services.ApartmentService;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private ApartmentRepo apartmentRepo;

	// saving apartment

	@Override
	public ApartmentDto saveApartment(ApartmentDto apartmentDto) {
		// TODO Auto-generated method stub
		try {
			Apartment newApartment = this.dtoToApartment(apartmentDto);

			newApartment.setCreatedAt(LocalDateTime.now());

			Apartment savedApartment = this.apartmentRepo.save(newApartment);

			ApartmentDto newapartmentDto = this.apartmentToDto(savedApartment);

			return newapartmentDto;
		} catch (Exception e) {
			throw new InternalServerException("Intrnal Server Error");
		}

	}

	// Model mapper

	private ApartmentDto apartmentToDto(Apartment apartment) {
		ApartmentDto newapartment = modelmapper.map(apartment, ApartmentDto.class);
		return newapartment;
	}

	private Apartment dtoToApartment(ApartmentDto apartmDto) {
		Apartment apartment = modelmapper.map(apartmDto, Apartment.class);
		return apartment;
	}

	// delete apartment by id

	@Override
	public ApartmentDto deleteApartment(int id) {
		// TODO Auto-generated method stub

		Apartment apartment = this.apartmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id " + id));

		this.apartmentRepo.deleteById(id);
		return this.apartmentToDto(apartment);

	}

	// get apartment by apartment id
	@Override
	public ApartmentDto getApartment(int id) {

		Apartment apartment = this.apartmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id" + id));
		return this.modelmapper.map(apartment, ApartmentDto.class);
	}

	// get all apartment

	@Override
	public List<ApartmentDto> allApartment() {
		// TODO Auto-generated method stub
		List<ApartmentDto> listofapartment = new ArrayList<>();
		Iterable<Apartment> all = this.apartmentRepo.findAll();
		all.forEach(apartment -> {
			ApartmentDto map = this.modelmapper.map(apartment, ApartmentDto.class);
			listofapartment.add(map);
		});
		return listofapartment;
	}

	// update apartment by id

	@Override
	public ApartmentUpdateDto updateApartment(ApartmentUpdateDto apartment, int id) {
		// TODO Auto-generated method stub

		Apartment apartmentfound = this.apartmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id " + id));
		if (apartment.getAddress() != null) {
			apartmentfound.setAddress(apartment.getAddress());
		}
		if (apartment.getApproxDeliveryTime() != null) {
			apartmentfound.setApproxDeliveryTime(apartment.getApproxDeliveryTime());
		}

		if (apartment.getGateDeliveryPrice() != 0) {
			apartmentfound.setGateDeliveryPrice(apartment.getGateDeliveryPrice());
		}
		apartmentfound.setCreatedAt(LocalDateTime.now());

		if (apartment.getTowerDeliveryPrice() != 0) {
			apartmentfound.setTowerDeliveryPrice(apartment.getTowerDeliveryPrice());
		}
		if (apartment.getPincode() != null) {
			apartmentfound.setPincode(apartment.getPincode());
		}
		if (apartment.getName() != null) {
			apartmentfound.setName(apartment.getName());
		}

		Apartment updatedApartment = this.apartmentRepo.save(apartmentfound);

		return this.modelmapper.map(updatedApartment, ApartmentUpdateDto.class);
	}

}
