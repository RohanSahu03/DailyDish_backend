package com.rk.dailydish.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.rk.dailydish.entity.Role;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class UserDto {

	private Long id;
	@NotEmpty(groups = OnRegister.class)
	private String name;

	
    @Min(value=6000000000L , message = "Phone number must start with 6, 7, 8, or 9 and be 10 digits long.",groups = {OnRegister.class, OnLogin.class}) // Indian mobile numbers start from 6, 7, 8, or 9
    @Max(value=9999999999L ,  message = "Phone number must start with 6, 7, 8, or 9 and be 10 digits long.",groups = {OnRegister.class, OnLogin.class}) // Ensuring it's a 10-digit number
    @Digits(integer = 10, fraction = 0, message = "Phone number must start with 6, 7, 8, or 9 and be 10 digits long.",groups = {OnRegister.class, OnLogin.class})
	private Long phone;
	
	@NotEmpty(groups = {OnRegister.class, OnLogin.class})
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password must consist of uppercase,lowercase,number and specialcharacter !!",groups = {OnRegister.class, OnLogin.class})
	private String password;
	
	private LocalDateTime createdAt;
	private String address;
	private int totalOrder;
	private Long totalAmount;
	
	private Set<RoleDto> role = new HashSet<>();
	
}
