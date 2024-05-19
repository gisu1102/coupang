package com.example.coupang.controller;

import com.example.coupang.dto.ItemDTO;
import com.example.coupang.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OrderController {
    private final ItemServiceImpl itemService;


    //주문하기 페이지로 이동
    @GetMapping("/order")
    public String orderForm(Model model) {

        List<ItemDTO> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);

        return "order";
    }
    //주문 수행 요청
    @PostMapping("/order/do")
    public String order() {

        return "order";
    }


    @GetMapping("/orderList")
    public String orderListForm() {

        return "orderList";
    }
}
