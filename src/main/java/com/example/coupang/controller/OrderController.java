package com.example.coupang.controller;

import com.example.coupang.dto.CartItemDTO;
import com.example.coupang.dto.ItemDTO;
import com.example.coupang.dto.OrderItemDTO;
import com.example.coupang.service.ItemServiceImpl;
import com.example.coupang.service.OrderService;
import jakarta.servlet.http.HttpSession;
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
    private final OrderService orderService;


    //주문하기 페이지로 이동
    @GetMapping("/order")
    public String orderForm(Model model) {

        List<ItemDTO> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);

        return "order";
    }

    //주문 목록 페이지
    @GetMapping("/orderList")
    public String orderListForm(HttpSession httpSession, Model model) {

        Long memberId = (Long) httpSession.getAttribute("memberId");
        List<OrderItemDTO> orderItems = orderService.getOrderItems(memberId);
        model.addAttribute("orderItems", orderItems);
        return "orderList";
    }

}
