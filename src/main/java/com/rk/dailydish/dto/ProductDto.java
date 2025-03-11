package com.rk.dailydish.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


	private int id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description ;
	
	@NotEmpty
	private String category;
	
	@NotEmpty
	private String image;
	
	@NotNull
	private double price;
	
	@NotEmpty
	private String unit;
	
	@Min(value=0,message="stock can not be less than 0")
	@NotNull(message="stock can not be null")
	private int stock;
	
	@NotNull(message="stock can not be null")
	@Min(value=0,message="remaining stock can not be less than 0")
	private int remainingStock;
	
	@NotNull(message="stock can not be null")
	private boolean status;
}
