package com.example.coupang.dto;

import com.example.coupang.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberResponseDto {

    private Long id; // 고유 식별자
    private String userEmail; // 이메일
    private String userPassword; // 비밀번호
    private String userNickname; // 닉네임
    private String birthday;    // 생일

    public MemberResponseDto(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.userEmail = memberEntity.getUserEmail();
        this.userPassword = memberEntity.getUserPassword();
        this.userNickname = memberEntity.getUserNickname();
        this.birthday = memberEntity.getBirthday();
    }

}
