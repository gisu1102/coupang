package com.example.coupang.controller;

import com.example.coupang.dto.ItemDTO;
import com.example.coupang.dto.MemberAddressDTO;
import com.example.coupang.repository.MemberRepository;
import com.example.coupang.service.MemberAddressService;
import com.example.coupang.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final MemberServiceImpl memberService;
    private final MemberAddressService memberAddressService;

    @GetMapping("/myPage/addAddress")
    public String addAddressFrom(){
        return "addAddress";
    }
    @PostMapping("/myPage/addAddress")
    public String addAddress(@ModelAttribute MemberAddressDTO addressDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        addressDTO.setMemberId(memberId);
        memberAddressService.addAddress(addressDTO);
        return "myPage"; //
    }

    @GetMapping("/selectAddress")
    public String selectAddressForm(Model model, HttpSession session){
        Long memberId = (Long) session.getAttribute("memberId");

        List<MemberAddressDTO> addressList = memberAddressService.getAllAddressesByMemberId(memberId);
        model.addAttribute("addressList", addressList);



        return "selectAddress";
    }

    @PostMapping("/selectAddress")
    public String selectAddress(){



        return "selectAddress";
    }




}
