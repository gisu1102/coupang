package com.example.coupang.dto;

import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.ItemEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class CartDTO {

    private Long id;
    private Double cartTotal;
    private Long memberId;
    private List<CartItemDTO> cartItems;

    public CartDTO (CartEntity cartEntity) {
        this.id = cartEntity.getId();
        this.cartTotal = cartEntity.getCartTotal();
        this.memberId = cartEntity.getMemberEntity().getId();
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItemListEntity()) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setCartId(cartEntity.getId());
            cartItemDTO.setItemId(cartItemEntity.getItemEntity().getId());
            cartItemDTOs.add(cartItemDTO);
        }
        this.cartItems = cartItemDTOs;

    }

    public static ItemDTO toItemDTO(ItemEntity itemEntity) {
        return new ItemDTO(itemEntity);
    }




}
