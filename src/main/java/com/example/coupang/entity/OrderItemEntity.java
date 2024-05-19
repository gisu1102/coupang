package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue
    @Column (name = "orderItem_id")
    private Long id;
    private double price;


    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private  ItemEntity itemEntity;

    // 생성자
    public OrderItemEntity(OrderEntity orderEntity, ItemEntity itemEntity) {
        this.orderEntity = orderEntity;
        this.itemEntity = itemEntity;
        this.price = itemEntity.getItemPrice();
    }

}
