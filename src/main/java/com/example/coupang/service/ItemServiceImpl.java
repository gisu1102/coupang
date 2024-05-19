package com.example.coupang.service;

import com.example.coupang.dto.ItemDTO;
import com.example.coupang.dto.MemberDTO;
import com.example.coupang.entity.ItemEntity;
import com.example.coupang.entity.MemberEntity;
import com.example.coupang.repository.ItemRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl {
    private final ItemRepository itemRepository;

    public void itemAdd(ItemDTO itemDTO) {
        ItemEntity itemEntity = ItemEntity.toItemEntity(itemDTO);
        itemRepository.save(itemEntity);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }

    public ItemDTO updateForm(Long itemId) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(itemId);;
        if (optionalItemEntity.isPresent()) {
            return ItemDTO.toItemDTO(optionalItemEntity.get());
        } else {
            return null;
        }
    }
    public void update(ItemDTO itemDTO , Long itemId) {

        Optional<ItemEntity> optionalFindMember = itemRepository.findById(itemId);
        ItemEntity itemToUpdate = optionalFindMember.get();
        itemToUpdate.itemUpdate(itemDTO);

        itemRepository.save(itemToUpdate);
    }

    public void delete(Long itemId) {
        Optional<ItemEntity> optionalFindMember = itemRepository.findById(itemId);
        ItemEntity itemToDelete= optionalFindMember.get();
        itemRepository.deleteById(itemId);
    }


    public List<ItemDTO> getAllItems() {
        List<ItemEntity> itemEntityList = itemRepository.findAllBy();
        List<ItemDTO> itemDtoList = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntityList) {
            ItemDTO itemDTO = new ItemDTO(itemEntity);
            itemDtoList.add(itemDTO);
        }

        // DiaryListDto 생성 후 반환
        return  itemDtoList;



    }
}
