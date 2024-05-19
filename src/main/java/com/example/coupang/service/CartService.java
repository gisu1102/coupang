package com.example.coupang.service;

import com.example.coupang.dto.CartDTO;
import com.example.coupang.dto.CartItemDTO;
import com.example.coupang.entity.CartEntity;
import com.example.coupang.entity.CartItemEntity;
import com.example.coupang.entity.ItemEntity;
import com.example.coupang.entity.MemberEntity;
import com.example.coupang.repository.CartItemRepository;
import com.example.coupang.repository.CartRepository;
import com.example.coupang.repository.ItemRepository;
import com.example.coupang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        /*
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartEntity.getCartItemListEntity()) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setCartId(cartEntity.getId());
            cartItemDTO.setItemId(cartItemEntity.getItemEntity().getId());
            cartItemDTOs.add(cartItemDTO);
        }
        return cartItemDTOs;

         */
    }

    public void cartAdd(Long memberId,  Long itemId ){
        Optional<CartEntity> optionalCartEntity  = cartRepository.findByMemberEntityId(memberId);
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);

        //장바구니 없을 시 장바구니 생성
        if (optionalCartEntity.isEmpty()) {
            CartEntity cartEntity = new CartEntity(optionalMemberEntity.get());
            cartRepository.save(cartEntity);
            // 존재하지 않는 아이템
            log.info("[ERROR] 아이템 존재안합니다.");
        }
        CartEntity cartEntity = cartRepository.findByMemberEntityId(memberId).get();
        ItemEntity itemEntity = optionalItemEntity.get();

        CartItemEntity CartItemEntity = new CartItemEntity(cartEntity, itemEntity) ;
        cartEntity.addCartItem(CartItemEntity);

        cartRepository.save(cartEntity);


    }

    public void cartDelete(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }


    public void cartBuyingAll(Long memberId) {
    }
}
