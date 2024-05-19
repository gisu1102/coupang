package com.example.coupang.entity;

import com.example.coupang.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private int wowMember;
    private String memberAddress;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }

}



/*
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 고유 식별자
    private String memberName; // 이름
    private String memberEmail; // 이메일
    private String memberPassword; // 비밀번호
    private String memberPhoneNumber; // 전화번호
    private String memberAddress; // 주소
    private String wowMembership; // 와우회원유무



    public MemberEntity(String memberName ,String memberEmail, String memberPassword,
                        String memberPhoneNumber, String memberAddress , String wowMembership) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberAddress = memberAddress;
        this.wowMembership = wowMembership;
    }

 */

