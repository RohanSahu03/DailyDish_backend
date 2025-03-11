package com.rk.dailydish.services;

import java.util.List;

import com.rk.dailydish.dto.ProductDto;
import com.rk.dailydish.dto.ProductUpdateDto;


public interface ProductService {

	ProductDto saveProduct(ProductDto product);
	
	ProductDto deleteProduct(int id);
	
	List<ProductDto> getAllProduct();
	
	ProductDto getProduct(int id);
	
	ProductUpdateDto updateProduct(ProductUpdateDto product,int id);
	
}
