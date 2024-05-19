package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "cartItem_id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="item_id" , nullable = false)
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cartEntity;



    public CartItemEntity(CartEntity cartEntity,ItemEntity itemEntity) {
        this.cartEntity = cartEntity;
        this.itemEntity= itemEntity;

    }






}
