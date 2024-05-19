package com.example.coupang.repository;

import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByMemberEntityId(Long memberId);
}
