package com.shop.Control;

import com.shop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //주문내역 페이지 요청
    @GetMapping("/orderList")
    public String orderList(Model model){

        return "order/order";
    }


}
