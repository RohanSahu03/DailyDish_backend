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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;
	@NotEmpty(groups = {OnRegister.class})
	private String name;
//	groups = {OnRegister.class, OnLogin.class}
	
	@NotEmpty(groups = {OnRegister.class, OnLogin.class})
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must start with 6, 7, 8, or 9 and be exactly 10 digits long.",groups = {OnRegister.class, OnLogin.class})
	private String phone;
	
	@NotEmpty(groups = {OnRegister.class, OnLogin.class})
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password must consist of uppercase,lowercase,number and specialcharacter !!",groups = {OnRegister.class, OnLogin.class})
	private String password;
	
	private LocalDateTime createdAt;
	private String address;
	private int totalOrder;
	private Long totalAmount;
	
	private Set<RoleDto> role = new HashSet<>();
	
}
