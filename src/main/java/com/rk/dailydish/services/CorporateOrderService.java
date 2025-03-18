package com.rk.dailydish.services;

import java.util.List;


import com.rk.dailydish.payload.OrderRequest;
import com.rk.dailydish.payload.OrderResponse;
import com.rk.dailydish.payload.OrderResponseWithPagination;


public interface CorporateOrderService {

	 OrderResponse createOrder(OrderRequest order);
	
	List<OrderResponse> getOrdersByUser(int userId);
	 
	OrderResponseWithPagination getAllOrders(Integer pageNumber,Integer pageSize);
}
