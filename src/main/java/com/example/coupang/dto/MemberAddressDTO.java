package com.example.coupang.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class MemberAddressDTO {
    private Long id;
    private String zipcode;
    private String addressDetail;
    private Long memberId;

}
