package com.rk.dailydish.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OrderItemRequest {
    private int productId;
    private int quantity;
}
