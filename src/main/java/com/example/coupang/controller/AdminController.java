package com.example.coupang.controller;

import com.example.coupang.dto.ItemDTO;
import com.example.coupang.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final ItemServiceImpl itemService;


    // 관리자 화면
    @GetMapping("/admin")
    public String adminForm() {
        return "admin";
    }

    // 관리자 상품 추가 폼 생성
    @GetMapping("/adminAdd")
    public String adminAddForm() {
        return "adminAdd";
    }

    // 관리자 상품 추가 요청
    @PostMapping("/adminAdd")
    public String adminAdd(@ModelAttribute ItemDTO itemDTO,Model model) {
        itemService.itemAdd(itemDTO);

        List<ItemDTO> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);


        return "/admin";
    }

    // 관리자 상품 삭제 요청
    @PostMapping("/adminDelete/{itemId}")
    public String adminDelete(@PathVariable Long itemId, Model model) {
        itemService.delete(itemId);

        List<ItemDTO> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);

        return "/admin";
    }

    // 관리자 상품 수정 폼
    @GetMapping("/adminUpdate/{itemId}")
    public String adminUpdateForm(@PathVariable Long itemId, Model model) {
        ItemDTO itemDTO = itemService.updateForm(itemId);
        if (itemDTO != null) {
            model.addAttribute("itemDTO", itemDTO);
            return "adminUpdate";
        } else {
            return "redirect:/admin";
        }
    }

    // 관리자 상품 수정 요청
    @PostMapping("/adminUpdate/{itemId}")
    public String adminUpdate(@ModelAttribute ItemDTO itemDTO, @PathVariable Long itemId, Model model) {
        itemService.update(itemDTO, itemId);

        List<ItemDTO> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);

        return "/admin";
    }
}

