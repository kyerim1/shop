package com.shop.Dto;

import com.shop.Entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    // 주문 상품 정보 저장 하여 주문 내역과 테이블저장 전에 사용
    private String itemName;
    private int quantity;
    private int price; // 상품 가격
    private int orderPrice; // 결제 금액
    private String imgUrl; //상품 대표이미지

    public OrderItemDto(OrderItem orderItem , String imgUrl){
        this.imgUrl=imgUrl;
        this.itemName= orderItem.getItem().getItemName();
        this.price=orderItem.getPrice();
        this.orderPrice=orderItem.getOrderPrice();
        this.quantity=orderItem.getQuantity();
    }
}
