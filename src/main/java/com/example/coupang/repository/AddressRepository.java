package com.example.coupang.repository;

import com.example.coupang.entity.MemberAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<MemberAddressEntity, Long> {


    List<MemberAddressEntity> findByMemberEntityId(Long memberId);
}
