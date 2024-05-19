package com.example.coupang.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class OrderDTO {

    private Long id;
    private Long memberId;
    private double totalPrice;
    private String status;
    private List<OrderItemDTO> orderItemDTOs;


}
