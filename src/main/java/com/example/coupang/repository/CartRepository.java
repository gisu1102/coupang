package com.example.coupang.repository;

import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByMemberEntityId(Long memberId);

}
