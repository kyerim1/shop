package com.shop.Service;

import com.shop.Dto.OrderDto;
import com.shop.Entity.Item;
import com.shop.Entity.Member;
import com.shop.Entity.Order;
import com.shop.Entity.OrderItem;
import com.shop.Repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
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
}
