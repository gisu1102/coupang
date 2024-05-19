package com.example.coupang.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberAddressEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String zipcode; //우편번호
    private String addressDetail; //상세 주소



    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;







}
