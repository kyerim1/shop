package com.shop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    // 상품 화면에서 구매하기 버튼클릭시 상품번호와 수량 받을DTO
    private Long itemId;  // 상품번호
    private int quantity; //수량
}
