package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListDto {
    private Long cartItemId; // CartItem  id
    private String itemName; //상품 이름
    private String imgUrl; //상품 대표 이미지
    private int price; // 상품 가격
    private int count; // 장바구니에 담은 상품 수량

    //Dto 객체 생성자 메서드
    public CartListDto(Long cartItemId, String itemName,
                       String imgUrl, int price, int count){
        this.cartItemId=cartItemId;
        this.itemName=itemName;
        this.count=count;
        this.imgUrl=imgUrl;
        this.price=price;
    }
}
