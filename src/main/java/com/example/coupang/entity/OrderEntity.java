package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column (name = "order_id")
    private Long id;
    private Double totalPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemListEntity;


    public OrderEntity(MemberEntity memberEntity ){
        this.memberEntity = memberEntity;
        this.totalPrice = 0.0;
        this.orderItemListEntity = new ArrayList<>();

    }
    public void addOrderItem(List<OrderItemEntity> orderItemEntity, String status ){
        this.orderItemListEntity.addAll(orderItemEntity);
        this.status = status;

    }



}
