package com.rk.dailydish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rk.dailydish.dto.CorporateDto;
import com.rk.dailydish.dto.CorporateUpdateDto;
import com.rk.dailydish.services.CorporateService;
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
@RequestMapping("/api/corporate")
public class CorporateController {
	
	@Autowired
	private CorporateService corporateService;

	@PostMapping("/")
	public ResponseEntity<CorporateDto> saveCorporate(@Valid @RequestBody CorporateDto corporateDto) {
		//TODO: process POST request
		CorporateDto saveCorporate = this.corporateService.saveCorporate(corporateDto);
		
		if(saveCorporate==null) {
			return new ResponseEntity<CorporateDto>(saveCorporate,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CorporateDto>(saveCorporate,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CorporateDto> deleteCorporate( @Valid @PathVariable int id){
		
		CorporateDto deleteCorporate = this.corporateService.deleteCorporate(id);
		
		return new ResponseEntity<CorporateDto>(deleteCorporate,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CorporateDto>> getAllCorporate(){
		
		List<CorporateDto> allCorporate = this.corporateService.allCorporate();
		return new ResponseEntity<List<CorporateDto>>(allCorporate,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CorporateDto> getAllCorporate(@Valid @PathVariable int id){
		
		 CorporateDto corporate = this.corporateService.getCorporate(id);
		return new ResponseEntity<CorporateDto>(corporate,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CorporateUpdateDto> getCorporate(@Valid @RequestBody CorporateUpdateDto corporate,@PathVariable int id){
		
		CorporateUpdateDto updateCorporate = this.corporateService.updateCorporate(corporate, id);
		
		return new ResponseEntity<CorporateUpdateDto>(updateCorporate,HttpStatus.OK);
	}
	
	
}
