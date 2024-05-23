package com.example.coupang.service;

import com.example.coupang.dto.CartItemDTO;
import com.example.coupang.dto.OrderItemDTO;
import com.example.coupang.entity.*;
import com.example.coupang.repository.CustomRepository;
import com.example.coupang.repository.OrderItemRepository;
import com.example.coupang.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomRepository customRepository;

    public List<OrderItemDTO> getOrderItems(Long memberId) {
        Optional<OrderEntity> optionalOrderEntity  = customRepository.findByMemberEntityId(memberId);
        OrderEntity orderEntity = optionalOrderEntity.get();

        List<OrderItemEntity> orderItemEntityList = customRepository.findAllByOrderEntity(orderEntity);

        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        for (OrderItemEntity orderItemEntity : orderItemEntityList) {
            ItemEntity itemEntity = orderItemEntity.getItemEntity();
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setId(orderItemEntity.getId());
            orderItemDTO.setOrderId(orderEntity.getId());
            orderItemDTO.setItemId(orderItemEntity.getItemEntity().getId());
            //상품에대한 상세 정보 전달
            orderItemDTO.setItemName(itemEntity.getItemName()); // 상품명 설정
            orderItemDTO.setItemPrice(itemEntity.getItemPrice()); // 가격 설정
            orderItemDTO.setItemDetail(itemEntity.getItemDetail()); // 상세 정보 설정

            orderItemDTOs.add(orderItemDTO);
        }
        return orderItemDTOs;
    }
}
