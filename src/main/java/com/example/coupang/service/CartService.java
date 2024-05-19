package com.example.coupang.service;

import com.example.coupang.dto.CartDTO;
import com.example.coupang.dto.CartItemDTO;
import com.example.coupang.entity.*;
import com.example.coupang.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public List<CartItemDTO> getCartItems(Long memberId) {
        Optional<CartEntity> optionalCartEntity  = cartRepository.findByMemberEntityId(memberId);
        CartEntity cartEntity = optionalCartEntity.get();

        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByCartEntity(cartEntity);

        List<CartItemDTO> cartItemDTOs = new ArrayList<>();

        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            ItemEntity itemEntity = cartItemEntity.getItemEntity();
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setCartId(cartEntity.getId());
            cartItemDTO.setItemId(cartItemEntity.getItemEntity().getId());
            //상품에대한 상세 정보 전달
            cartItemDTO.setItemName(itemEntity.getItemName()); // 상품명 설정
            cartItemDTO.setItemPrice(itemEntity.getItemPrice()); // 가격 설정
            cartItemDTO.setItemDetail(itemEntity.getItemDetail()); // 상세 정보 설정

            cartItemDTOs.add(cartItemDTO);
        }
        return cartItemDTOs;
    }

    public void cartAdd( Long itemId ,Long memberId ){
        Optional<CartEntity> optionalCartEntity  = cartRepository.findByMemberEntityId(memberId);
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);

        //장바구니 없을 시 장바구니 생성
        if (optionalCartEntity.isEmpty()) {
            CartEntity cartEntity = new CartEntity(optionalMemberEntity.get());
            cartRepository.save(cartEntity);
            // 존재하지 않는 아이템
            log.info("장바구니 생성.");
        }
        else{
            CartEntity cartEntity = optionalCartEntity.get();
            if (optionalItemEntity.isPresent()) {
                ItemEntity itemEntity = optionalItemEntity.get();
                CartItemEntity cartItemEntity = new CartItemEntity(cartEntity, itemEntity);
                cartEntity.addCartItem(cartItemEntity);
                cartRepository.save(cartEntity);
            } else {
                log.error("존재하지 않는 아이템입니다.");
            }
        }

    }

    public void cartDelete(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }


    public void cartBuyingAll(Long memberId) {
        //로그인한 사용자 엔티티
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
        // 해당 멤버의 장바구니 정보 가져오기
        Optional<CartEntity> optionalCartEntity = cartRepository.findByMemberEntityId(memberId);
        //예외처리 로직 구현 --
        CartEntity cartEntity = optionalCartEntity.get();
        // 장바구니 아이템 목록 가져오기
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByCartEntity(cartEntity);

        Optional<OrderEntity> optionalOrderEntity = orderRepository.findByMemberEntityId(memberId);
        //장바구니 없을 시 장바구니 생성
        if (optionalOrderEntity.isEmpty()) {
            OrderEntity orderEntity = new OrderEntity(optionalMemberEntity.get());
            orderRepository.save(orderEntity);
            // 존재하지 않는 아이템
            log.info("주문 생성.");
        }
        OrderEntity orderEntity =  orderRepository.findByMemberEntityId(memberId).get();
        //장바구니 안의 아템 주문 아이템으로 이동
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (CartItemEntity cartItem : cartItemEntityList) {
            OrderItemEntity orderItemEntity =
                    new  OrderItemEntity(orderEntity, cartItem.getItemEntity());
            orderItemEntities.add(orderItemEntity);
        }

        //주문 엔티티에 주문 아이템 저장
        orderEntity.addOrderItem(orderItemEntities , "주문 완료");
        //주문 엔티티 save
        orderRepository.save(orderEntity);
        //장바구니 화면 초기화
        //cartRepository.deleteAllByMemberEntityId(memberId);





    }
}
