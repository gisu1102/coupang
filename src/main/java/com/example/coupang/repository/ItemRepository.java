package com.example.coupang.repository;

import com.example.coupang.entity.ItemEntity;
import com.example.coupang.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository  extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findById(Long id);
    List<ItemEntity> findAllBy();


}
