package com.rk.dailydish.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApartmentDto {

	private int id;

	private LocalDateTime createdAt;

	@NotEmpty
	private String name;

	@NotEmpty
	private String Address;

	@NotEmpty
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian PIN code. It must be a 6-digit number starting from 1-9.")
	private String pincode;

	private int towerDeliveryPrice;

	private int gateDeliveryPrice;

	@NotEmpty
	private String approxDeliveryTime;
}
