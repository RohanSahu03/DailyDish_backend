package com.rk.dailydish.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.dailydish.dto.ProductDto;
import com.rk.dailydish.dto.ProductUpdateDto;
import com.rk.dailydish.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto product) {
		ProductDto saveProduct = this.productService.saveProduct(product);
		return new ResponseEntity<ProductDto>(saveProduct,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getAllProduct() {
		List<ProductDto> allProduct =  this.productService.getAllProduct();
		
		return new ResponseEntity<List<ProductDto>>(allProduct,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable int id) {
		ProductDto product = this.productService.getProduct(id);
		
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable int id){
		ProductDto deleteProduct = this.productService.deleteProduct(id);
		return new ResponseEntity<ProductDto>(deleteProduct,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductUpdateDto> updateProduct(@Valid @RequestBody ProductUpdateDto product,@PathVariable int id){
		ProductUpdateDto updateProduct = this.productService.updateProduct(product, id);
		
		return new ResponseEntity<ProductUpdateDto>(updateProduct,HttpStatus.OK);
	}
	
}
