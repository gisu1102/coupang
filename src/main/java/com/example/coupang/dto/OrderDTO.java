package com.example.coupang.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private Long memberId;
    private double totalPrice;
    private String status;
    private List<OrderItemDTO> orderItems;


}
