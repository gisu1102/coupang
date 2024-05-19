package com.example.coupang.repository;

import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByCartEntity(CartEntity cartEntity);


}
