package com.rk.dailydish.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;


import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentUpdateDto {
	private int id;

	private LocalDateTime createdAt;

	
	private String name;


	private String Address;


	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian PIN code. It must be a 6-digit number starting from 1-9.")
	private String pincode;

	private int towerDeliveryPrice;

	private int gateDeliveryPrice;

	private String approxDeliveryTime;
}
