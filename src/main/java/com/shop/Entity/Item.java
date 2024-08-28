package com.shop.Entity;

import com.shop.constant.ItemCategory;
import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Setter   @Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id")
    private Long id;

    private String itemName;
    private int price;
    private int stock;

    @Type( type="org.hibernate.type.TextType")
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

}
