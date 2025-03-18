package com.rk.dailydish.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rk.dailydish.entity.ApartmentOrders;
import com.rk.dailydish.entity.Product;
import com.rk.dailydish.entity.User;
import com.rk.dailydish.exceptions.InsufficientStock;
import com.rk.dailydish.exceptions.ResourceNotFoundException;
import com.rk.dailydish.payload.OrderItem;
import com.rk.dailydish.payload.OrderRequest;
import com.rk.dailydish.payload.OrderResponse;
import com.rk.dailydish.payload.OrderResponseWithPagination;
import com.rk.dailydish.repository.ApartmentOrderRepo;
import com.rk.dailydish.repository.ProductRepo;
import com.rk.dailydish.repository.UserRepository;
import com.rk.dailydish.services.ApartmentOrderService;

@Service
public class ApartmentOrdersImpl implements ApartmentOrderService {

@Autowired
private ApartmentOrderRepo apartmentOrderRepo;

@Autowired
ModelMapper mapper;

@Autowired
private UserRepository userRepository;

@Autowired
private ProductRepo productRepository;

@Override
public OrderResponse createOrder(OrderRequest orderRequest) {
    User user = userRepository.findById((long) orderRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    String phone = user.getPhone();

    ApartmentOrders order = new ApartmentOrders();
    order.setUser(user);
    order.setPhone(phone);
    order.setSlotTime(orderRequest.getSlotTime());
    order.setApartmentName(orderRequest.getApartmentName());
    order.setLocation(orderRequest.getLocation());
    order.setDeliveryType(orderRequest.getDeliveryType());
    order.setPaymentMethods(orderRequest.getPaymentMethod());
    order.setDeliveryCharge(orderRequest.getDeliveryCharge());
    order.setTax(orderRequest.getTax());
    order.setTotalAmount(orderRequest.getTotalAmount());

    // Convert OrderItemRequest to OrderItem and attach to order
    order.setOrderItems(orderRequest.getOrderedItems().stream().map(item -> {
    
        Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        // Reduce the remaining stock
        if (product.getRemainingStock() < item.getQuantity()) {
            throw new InsufficientStock("Insufficient stock for product: " + product.getName());
        }
     
        product.setRemainingStock(product.getRemainingStock() - item.getQuantity());

        // Save the updated product stock in the database
        productRepository.save(product);
     
        return new OrderItem(product.getId(), product.getName(), product.getDescription(),
                product.getCategory(), product.getImage(), product.getPrice(), product.getUnit(), item.getQuantity());
    }).collect(Collectors.toList()));

    ApartmentOrders savedOrder = apartmentOrderRepo.save(order);

    return OrderResponse.builder()
            .id(savedOrder.getId())
            .slotTime(savedOrder.getSlotTime())
            .userId(savedOrder.getUser().getId().intValue())
            .apartmentName(savedOrder.getApartmentName())
            .location(savedOrder.getLocation())
            .deliveryType(savedOrder.getDeliveryType())
            .paymentStatus(savedOrder.getPaymentMethods())
            .deliveryCharge(savedOrder.getDeliveryCharge())
            .tax(savedOrder.getTax())
            .totalAmount(savedOrder.getTotalAmount())
            .orderStatus("PLACED")
            .date(LocalDateTime.now())
            .phone(savedOrder.getPhone())
            .build();
}

@Override
public List<OrderResponse> getOrdersByUser(int userId) {
    List<ApartmentOrders> orders = apartmentOrderRepo.findByUserId((long) userId);
        
    return orders.stream().map(order -> mapToResponse(order)).collect(Collectors.toList());
}


@Override
public OrderResponseWithPagination getAllOrders(Integer pageNumber,Integer pageSize) {
	
	Pageable p = PageRequest.of(pageNumber, pageSize);
	
Page<ApartmentOrders> pageOrders = apartmentOrderRepo.findAll(p);

    List<OrderResponse> orders = pageOrders.stream().map(this::mapToResponse).collect(Collectors.toList());
    
    OrderResponseWithPagination orderResponseWithPagination = new OrderResponseWithPagination();
    
    orderResponseWithPagination.setContent(orders);
    orderResponseWithPagination.setPageNumber(pageOrders.getNumber());
    orderResponseWithPagination.setPageSize(pageOrders.getSize());
    orderResponseWithPagination.setTotalElements(pageOrders.getTotalElements());
    orderResponseWithPagination.setTotalPages(pageOrders.getTotalPages());
    orderResponseWithPagination.setLastPage(pageOrders.isLast());
    
    return orderResponseWithPagination;
}

//cancel order

//public void cancelOrder(int orderId) {
//    ApartmentOrders order = apartmentOrderRepo.findById(orderId)
//            .orElseThrow(() -> new RuntimeException("Order not found"));
//    orderRepository.delete(order);
//}


private OrderResponse mapToResponse(ApartmentOrders order) {
    return OrderResponse.builder()
            .id(order.getId())
            .slotTime(order.getSlotTime())
            .userId(order.getUser().getId().intValue())
            .apartmentName(order.getApartmentName())
            .location(order.getLocation())
            .deliveryType(order.getDeliveryType())
            .paymentStatus(order.getPaymentMethods())
            .deliveryCharge(order.getDeliveryCharge())
            .tax(order.getTax())
            .totalAmount(order.getTotalAmount())
            .orderStatus("PLACED")
            .date(LocalDateTime.now())
            .phone(order.getPhone())
            .orderedItems(order.getOrderItems())
            .build();
}

}
