package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue
    @Column (name = "cart_id")
    private Long id;
    private Double cartTotal;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemListEntity;



    public CartEntity(MemberEntity memberEntity){
        this.memberEntity = memberEntity;
        this.cartTotal = 0.0;
        this.cartItemListEntity = new ArrayList<>();

    }

    public void addCartItem(CartItemEntity cartItemEntity) {

       this.cartItemListEntity.add(cartItemEntity) ;
    }



}
