package com.rk.dailydish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.dailydish.entity.ApartmentOrders;
import com.rk.dailydish.entity.User;


public interface ApartmentOrderRepo extends JpaRepository<ApartmentOrders, Integer>{

	 List<ApartmentOrders> findByUserId(Long userId);
	
}
