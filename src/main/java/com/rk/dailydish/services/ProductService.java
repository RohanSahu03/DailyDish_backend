package com.rk.dailydish.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rk.dailydish.dto.ProductDto;
import com.rk.dailydish.dto.ProductUpdateDto;


public interface ProductService {

	ProductDto saveProduct(ProductDto product,String filename);
	
	ProductDto deleteProduct(int id);
	
	List<ProductDto> getAllProduct();
	
	ProductDto getProduct(int id);
	
	ProductUpdateDto updateProduct(ProductUpdateDto product,int id);
	
	String updateImage(MultipartFile file,int id);
	
}
