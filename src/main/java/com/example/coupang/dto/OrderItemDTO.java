package com.example.coupang.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long itemId;
    private int quantity;
    private double price;

    private String itemName; // 상품명 필드 추가
    private Double itemPrice; // 가격 필드 추가
    private String itemDetail; // 상세 정보 필드 추가


}
