package com.rk.dailydish.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.rk.dailydish.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
			
		Map<String,String> resp = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});	
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	 

	@ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ApiResponse> userAlreadyExistException(UserAlreadyExistException ex){
	  String message = ex.getMessage();
	  ApiResponse apiResponse = new ApiResponse(message,false);
	  return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FOUND);
	  
  }
	
	@ExceptionHandler(InsufficientStock.class)
	  public ResponseEntity<ApiResponse> insufficientStock(InsufficientStock ex){
		  String message = ex.getMessage();
		  ApiResponse apiResponse = new ApiResponse(message,false);
		  return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FOUND);
		  
	  }
	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<ApiResponse> internalServerException(InternalServerException ex){
		String message = ex.getMessage();
		System.out.println("message"+message);
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> userNotFoundException(UserNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	

	
	@ExceptionHandler(InvalidPhoneOrPasswordException.class)
	public ResponseEntity<ApiResponse> invalidPhoneOrPasswordException(InvalidPhoneOrPasswordException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGenericException(Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
