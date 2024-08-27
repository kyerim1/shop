package com.shop.Entity;

import com.shop.constant.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name="shop_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    private String userId;
    private String password;
    private String name;

    @Column(unique = true)
    private String email;
    private String addr1;
    private String addr2;
    private int zipCode;

    @Enumerated(EnumType.STRING)
    private Role role;

}
