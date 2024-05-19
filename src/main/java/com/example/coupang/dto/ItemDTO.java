package com.example.coupang.dto;

import com.example.coupang.entity.ItemEntity;
import com.example.coupang.entity.MemberEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Data
public class ItemDTO {

    private Long  id;
    private String itemName;
    private Double itemPrice;
    private String itemDetail;


        public ItemDTO (ItemEntity itemEntity) {
            this.id = itemEntity.getId();
            this.itemName = itemEntity.getItemName();
            this.itemDetail= itemEntity.getItemDetail();
            this.itemPrice = itemEntity.getItemPrice();
        }

        public static ItemDTO toItemDTO(ItemEntity itemEntity) {
            return new ItemDTO(itemEntity);
        }




}
