package com.rk.dailydish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.dailydish.entity.ApartmentOrders;
import com.rk.dailydish.entity.CorporateOrders;

public interface CorporateOrderRepo extends JpaRepository<CorporateOrders, Integer>{
	 List<CorporateOrders> findByUserId(Long userId);
}
