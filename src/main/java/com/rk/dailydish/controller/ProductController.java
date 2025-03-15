package com.rk.dailydish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rk.dailydish.dto.ProductDto;
import com.rk.dailydish.dto.ProductUpdateDto;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.services.FileService;
import com.rk.dailydish.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private FileService fileService;
	
	//to convert into Json from String
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> saveProduct(@Valid @RequestParam("image") MultipartFile file,@RequestParam("productData") String product) {
		
		ProductDto productDto = null;
		try {
			//converting string to JSON
			productDto = objectMapper.readValue(product, ProductDto.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filename=null;
		try {
			filename = this.fileService.uploadImage(path, file);
		}catch(Exception e) {
			throw new ResourceNotFoundException("File Already Exist");
		}
		
		ProductDto saveProduct = this.productService.saveProduct(productDto,filename);
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
	
	@PutMapping("/image/{id}")
	public ResponseEntity<String> updateProduct(@Valid @RequestParam("image") MultipartFile file,@PathVariable int id){
		String updateImage = this.productService.updateImage(file, id);
		
		return new ResponseEntity<String>(updateImage,HttpStatus.OK);
	}
	
}
