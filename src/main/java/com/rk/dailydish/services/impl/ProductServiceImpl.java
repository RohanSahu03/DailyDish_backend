package com.rk.dailydish.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rk.dailydish.dto.ProductDto;
import com.rk.dailydish.dto.ProductUpdateDto;
import com.rk.dailydish.entity.Product;
import com.rk.dailydish.exceptions.InternalServerException;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.repository.ProductRepo;
import com.rk.dailydish.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public ProductDto saveProduct(ProductDto product) {
		// TODO Auto-generated method stub
		
		try {
			Product produtmap = this.modelmapper.map(product, Product.class);
			
			Product savedProduct = this.productRepo.save(produtmap);
			
			ProductDto productdto = this.modelmapper.map(savedProduct, ProductDto.class);
			
			
			return productdto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InternalServerException("Internal server error");
		}
	}

	@Override
	public ProductDto deleteProduct(int id) {
		// TODO Auto-generated method stub
		
		  Product product  = this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id "+id));
		  
		  this.productRepo.deleteById(id);
		return this.modelmapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		// TODO Auto-generated method stub
		List<ProductDto> listofproducts = new ArrayList<>();
		Iterable<Product> all = this.productRepo.findAll();
		
		all.forEach(product -> {
			ProductDto map = this.modelmapper.map(product, ProductDto.class);
			listofproducts.add(map);
		});
		return listofproducts;
	}

	@Override
	public ProductDto getProduct(int id) {
		// TODO Auto-generated method stub
		
		Product product = this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id "+id));
		
		return this.modelmapper.map(product, ProductDto.class);
	}

	@Override
	public ProductUpdateDto updateProduct(ProductUpdateDto product, int id) {
		// TODO Auto-generated method stub
		
		Product foundproduct = this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id "+id));
		
		if(product.getName() != null) {
			foundproduct.setName(product.getName());
		}
		if(product.getCategory() != null) {
			foundproduct.setCategory(product.getCategory());
		}
		if(product.getDescription() != null) {
			foundproduct.setDescription(product.getDescription());
		}
		if(product.getPrice() != 0) {
			foundproduct.setPrice(product.getPrice());
		}
		if(product.getRemainingStock() != 0) {
			foundproduct.setRemainingStock(product.getRemainingStock());
		}
		
		if(product.getStock() != 0) {
			foundproduct.setStock(product.getStock());
		}
		if(product.getUnit() != null) {
			foundproduct.setUnit(product.getUnit());
		}
		if(product.getImage() != null) {
			foundproduct.setImage(product.getImage());
		}
		
		
		Product updatedProduct = this.productRepo.save(foundproduct);
		
		return this.modelmapper.map(updatedProduct, ProductUpdateDto.class);
	}

}
