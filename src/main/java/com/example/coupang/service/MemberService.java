package com.example.coupang.service;

import com.example.coupang.dto.MemberRequestDto;
import com.example.coupang.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto signUp(MemberRequestDto.MemberSignupReqDto signUpRequestDto);




}
