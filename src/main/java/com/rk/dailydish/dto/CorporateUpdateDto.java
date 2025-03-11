package com.rk.dailydish.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CorporateUpdateDto {

	private int id;

	private LocalDateTime createdAt;


	private String name;

	private String Address;

	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian PIN code. It must be a 6-digit number starting from 1-9.")
	private String pincode;

	private int towerDeliveryPrice;

	private String approxDeliveryTime;
}

