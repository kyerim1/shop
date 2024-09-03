package com.shop.Service;

import com.shop.Dto.OrderDto;
import com.shop.Dto.OrderHistDto;
import com.shop.Dto.OrderItemDto;
import com.shop.Entity.*;
import com.shop.Repository.*;
import com.shop.constant.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public Long orders(List<OrderDto> orderDtos, String userId) {
        // 현재 로그인한 회원 Member 객체
        Member member = memberRepository.findByUserId(userId);
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderDto orderDto : orderDtos){
            Item item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem =
                    OrderItem.createOrderItem(item, orderDto.getQuantity());
            orderItemList.add(orderItem);
        }
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }

    public Page<OrderHistDto> getOrderList(String userId, Pageable pageable) {
        // 현재 로그인한 회원의 주문내역 가져오기 - 페이징 갯수만큼(5개)
        List<Order> orders = orderRepository.findByOrders(userId, pageable);
        Long total = orderRepository.countOrder(userId);

        List<OrderHistDto> orderHistDtoList = new ArrayList<>();
        // 현재 로그인한 회원정보
        Member member = memberRepository.findByUserId(userId);

        for(Order order : orders){
            OrderHistDto orderHistDto = new OrderHistDto(order, member);
            //주문 상품들 entity->DTO
            List<OrderItem> orderItemList = order.getOrderItemList();
            for(OrderItem orderItem : orderItemList){
                // 주문 상품의 대표 이미지 가져오기 - 주문상품의 상품 번호와 Y
                ItemImg itemImg = itemImgRepository
                        .findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto =
                        new OrderItemDto(orderItem,itemImg.getImgUrl());
                // 주문내역DTO에 주문상품DTO 저장 - ArrayList에 add해서 저장하기
                orderHistDto.addOrderItemDto( orderItemDto);
            }
            orderHistDtoList.add( orderHistDto );
        }
        // 페이징을 위해 PageImpl 인터페이스로 반환
        return new PageImpl<>(orderHistDtoList, pageable, total);
    }

    //상품 상세 페이지에서 구매하기 버튼 클릭시
    public Long itemOrder(OrderDto orderDto, String userId) {
        Member member = memberRepository.findByUserId(userId);

        Item item = itemRepository.findById(orderDto.getItemId()).get();

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem =
                OrderItem.createOrderItem(item, orderDto.getQuantity());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById( orderId ).get();
        order.setOrderStatus( OrderStatus.CANCEL );

    }
}
