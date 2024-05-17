package com.example.coupang.controller;


import com.example.coupang.common.ApiResponse;
import com.example.coupang.dto.MemberRequestDto;
import com.example.coupang.dto.MemberResponseDto;
import com.example.coupang.service.MemberService;
import com.example.coupang.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j //logging
@RestController
public class MemberController {
    private final MemberServiceImpl memberService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody MemberRequestDto.MemberSignupReqDto signUpRequestDto) {
        log.info("[MemberController] signUp");
        MemberResponseDto result = memberService.signUp(signUpRequestDto);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member join success", result));
    }



}
