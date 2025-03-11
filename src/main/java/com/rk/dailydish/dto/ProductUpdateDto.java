package com.rk.dailydish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

	private int id;
	

	private String name;
	
	private String description ;
	
	private String category;
	

	private String image;
	

	private double price;
	

	private String unit;
	
	private int stock;
	
	private int remainingStock;
	
	private boolean status;
}
