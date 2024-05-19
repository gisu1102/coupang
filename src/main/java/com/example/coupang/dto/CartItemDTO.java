package com.example.coupang.dto;

import com.example.coupang.entity.ItemEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartItemDTO {

    private Long id;
    private Long cartId;
    private Long itemId;
    private String itemName; // 상품명 필드 추가
    private Double itemPrice; // 가격 필드 추가
    private String itemDetail; // 상세 정보 필드 추가


    @Getter
    public static class CartItemDTOS {
        private final List<CartItemDTOS> cartItemDTOSList;
        public CartItemDTOS(List<CartItemDTOS> cartItemDTOSList) {

            this.cartItemDTOSList = cartItemDTOSList;
        }

    }


}
