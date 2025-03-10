package com.rk.dailydish.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.dailydish.dto.CorporateDto;
import com.rk.dailydish.dto.CorporateUpdateDto;
import com.rk.dailydish.dto.CorporateDto;
import com.rk.dailydish.entity.Corporate;
import com.rk.dailydish.entity.Corporate;
import com.rk.dailydish.exceptions.InternalServerException;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.repository.CorporateRepo;
import com.rk.dailydish.services.CorporateService;

@Service
public class CorporateServiceImpl implements CorporateService {
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private CorporateRepo corporateRepo;

	@Override
	public CorporateDto saveCorporate(CorporateDto corporate) {
		// TODO Auto-generated method stub
		try {
			Corporate realCorporate = this.modelmapper.map(corporate, Corporate.class);
			realCorporate.setCreatedAt(LocalDateTime.now());
			Corporate savedCorporate = this.corporateRepo.save(realCorporate);
			return this.modelmapper.map(savedCorporate, CorporateDto.class);
			
		}catch(Exception e) {
			throw new InternalServerException("Intrnal Server Error");
		}
	
	}

	@Override
	public CorporateDto deleteCorporate(int id) {
		Corporate corporate = this.corporateRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Corporate not found with id " + id));

		this.corporateRepo.deleteById(id);
		return this.modelmapper.map(corporate, CorporateDto.class);
	}

	@Override
	public List<CorporateDto> allCorporate() {
		List<CorporateDto> listofcorporate = new ArrayList<>();
		Iterable<Corporate> all = this.corporateRepo.findAll();
		all.forEach(corporate -> {
			CorporateDto map = this.modelmapper.map(corporate, CorporateDto.class);
			listofcorporate.add(map);
		});
		return listofcorporate;
	}

	@Override
	public CorporateDto getCorporate(int id) {

		Corporate corporate = this.corporateRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Corporate not found with id " + id));
		return this.modelmapper.map(corporate, CorporateDto.class);
	}

	@Override
	public CorporateUpdateDto updateCorporate(CorporateUpdateDto corporate, int id) {

		Corporate corporatefound = this.corporateRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Corporate not found with id " + id));
		if (corporate.getAddress() != null) {
			corporatefound.setAddress(corporate.getAddress());
		}
		if (corporate.getApproxDeliveryTime() != null) {
			corporatefound.setApproxDeliveryTime(corporate.getApproxDeliveryTime());
		}

		corporatefound.setCreatedAt(LocalDateTime.now());

		if (corporate.getTowerDeliveryPrice() != 0) {
			corporatefound.setTowerDeliveryPrice(corporate.getTowerDeliveryPrice());
		}
		if (corporate.getPincode() != null) {
			corporatefound.setPincode(corporate.getPincode());
		}
		if (corporate.getName() != null) {
			corporatefound.setName(corporate.getName());
		}

		Corporate updatedcorporate = this.corporateRepo.save(corporatefound);

		return this.modelmapper.map(updatedcorporate, CorporateUpdateDto.class);

	}

}
