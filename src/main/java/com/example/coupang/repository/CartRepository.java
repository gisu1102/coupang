package com.example.coupang.repository;

import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByMemberEntityId(Long memberId);

    void deleteAllByMemberEntityId(Long memberId);
}
