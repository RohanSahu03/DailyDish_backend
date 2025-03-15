package com.rk.dailydish.services;

import com.rk.dailydish.payload.OrderRequest;
import java.util.List;
import com.rk.dailydish.payload.OrderResponse;

public interface ApartmentOrderService {

	
	 OrderResponse createOrder(OrderRequest order);
	
	List<OrderResponse> getOrdersByUser(int userId);
	 
	List<OrderResponse> getAllOrders();
	
//	void cancelOrder(int orderId)
}
