package com.example.coupang.service;

import com.example.coupang.dto.MemberRequestDto;
import com.example.coupang.dto.MemberResponseDto;
import com.example.coupang.entity.MemberEntity;
import com.example.coupang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberResponseDto signUp(MemberRequestDto.MemberSignupReqDto signUpRequestDto) {

        log.info("[MemberServiceImpl] signUp");
        MemberEntity memberEntity = signUpRequestDto.toEntity();
        Optional<MemberEntity> optionalFindMember = memberRepository.findByMemberEmail(memberEntity.getUserEmail());
        if (optionalFindMember.isPresent()) {
            // 중복 이메일 발생
            log.info("[ERROR] 중복 이메일 입니다.");
            return null;
        }
        memberRepository.save(memberEntity); // 중복 이메일이 없다면 DB에 저장하기.
        return new MemberResponseDto(memberEntity);
    }


}
