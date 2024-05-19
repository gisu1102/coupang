package com.example.coupang.entity;

import com.example.coupang.dto.ItemDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String itemName;
    private Double itemPrice;
    private String itemDetail;

    public ItemEntity(String itemName,Double itemPrice, String itemDetail) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDetail = itemDetail;
    }

    public static ItemEntity toItemEntity(ItemDTO itemDTO) {
        return new ItemEntity(
                itemDTO.getItemName(),
                itemDTO.getItemPrice(),
                itemDTO.getItemDetail());
    }

    //아이템 수정
    public void itemUpdate(ItemDTO itemDTO) {
        this.itemName = itemDTO.getItemName();
        this.itemDetail=itemDTO.getItemDetail();
        this.itemPrice=itemDTO.getItemPrice();
    }
}
