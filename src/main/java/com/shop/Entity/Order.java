package com.shop.Entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Order extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    private LocalDateTime orderDate;//주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrder( this );
    }

    public static Order createOrder( Member member, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMember( member );
        for( OrderItem orderItem : orderItemList){
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus( OrderStatus.ORDER );
        order.setOrderDate( LocalDateTime.now());
        return order;
    }


}
//양방향 관계 시  주인 엔티티 정의 - mappedBy="엔티티"
// cascade - 주인 엔티티의 저장,업데이트 삭제 등이  하인 엔티티(OrderItem)
//          에게도 동일하게 적용됨
// orphanRemoval - 주인 엔티티에서 하인 엔티티를 삭제하면,
//    실제 데이터베이스에서 도 삭제 가된다.
// fetch - 양방향 관계에서 는 주인,하인 모두 조회가 완료 되어야
//       사용가능하므로,  모두 조회 될때 까지 지연시켜줘야 한다.