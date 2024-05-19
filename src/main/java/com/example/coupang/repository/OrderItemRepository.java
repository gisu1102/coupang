package com.example.coupang.repository;

import com.example.coupang.entity.OrderEntity;
import com.example.coupang.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findAllByOrderEntity(OrderEntity orderEntity);
}
