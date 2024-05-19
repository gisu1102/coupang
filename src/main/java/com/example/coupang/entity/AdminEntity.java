package com.example.coupang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue
    @Column(name = "Admin_id")
    private Long id; // 고유 식별자
    private String adminName; // 이름
    private String adminEmail; // 이메일
    private String adminPassword; //pw

    public AdminEntity(String adminName,String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
    }
}
