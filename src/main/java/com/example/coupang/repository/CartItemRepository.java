package com.example.coupang.repository;

import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByCartEntity(CartEntity cartEntity);
}
