package com.example.coupang.dto;

import com.example.coupang.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberRequestDto {
    private String userEmail; // 이메일
    private String userPassword; // 비밀번호
    private String userNickname; // 닉네임
    private String birthday;    // 생일

    public MemberEntity toEntity() { // DTO를 User 엔티티로 변환하는 메소드
        return new MemberEntity(this.userEmail, this.userPassword, this.userNickname, this.birthday);
    }



    public static class MemberSignupReqDto {
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userNickname; // 닉네임
        private String birthday;    // 생일

        public MemberEntity toEntity() { // DTO를 User 엔티티로 변환하는 메소드
            return new MemberEntity(this.userEmail, this.userPassword, this.userNickname, this.birthday);
        }

    }
}
