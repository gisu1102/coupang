package com.example.coupang.controller;

import com.example.coupang.dto.CartDTO;
import com.example.coupang.dto.CartItemDTO;
import com.example.coupang.dto.ItemDTO;
import com.example.coupang.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CartContorller {

    private final CartService cartService;


    //장바구니 목록
    @GetMapping("/cart")
    public String showCart( HttpSession httpSession, Model model) {

        Long memberId = (Long) httpSession.getAttribute("memberId");
        List<CartItemDTO> cartItems = cartService.getCartItems(memberId);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    //장바구니 추가 폼


    //장바구니에 추가 요청
    @PostMapping("/cart/cartAdd/{itemId}")
    public String cartAdd( HttpSession httpSession, @PathVariable Long itemId) {
        log.info("ADDaddadd.");
        Long memberId = (Long) httpSession.getAttribute("memberId");

        cartService.cartAdd(itemId ,memberId);
        return "order";
    }

    //장바구니 항목 삭제 요청
    @DeleteMapping("/cart/cartDelete/{cartItemId}")
    public String cartDelete( @PathVariable Long cartItemId) {
        cartService.cartDelete(cartItemId);
        return "cart";
    }

    //장바구니 물건 전체 구매
    @PostMapping("/cart/buyAll")
    public String cartBuyingALl(HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("memberId");
        cartService.cartBuyingAll(memberId);

        return "cart";
    }


}
