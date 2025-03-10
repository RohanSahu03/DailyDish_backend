package com.rk.dailydish.services;

import java.util.List;

import com.rk.dailydish.dto.CorporateDto;
import com.rk.dailydish.dto.CorporateUpdateDto;

public interface CorporateService {

	CorporateDto saveCorporate(CorporateDto corporate);
	
	CorporateDto deleteCorporate(int id);
	
	List<CorporateDto> allCorporate();
	
	CorporateDto getCorporate(int id);
	
	CorporateUpdateDto updateCorporate(CorporateUpdateDto corporate,int id);
}
