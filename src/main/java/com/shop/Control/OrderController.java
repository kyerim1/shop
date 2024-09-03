package com.shop.Control;

import com.shop.Dto.OrderDto;
import com.shop.Dto.OrderHistDto;
import com.shop.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    //주문 취소 버튼클릭시
    @PostMapping("/orderCancel")
    public @ResponseBody ResponseEntity orderCancel(@RequestParam Long orderId){

        orderService.cancelOrder( orderId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }




    //상품 상세 페이지에서 즉시 구매하기 버튼 클릭시
    @PostMapping("/order")
    public @ResponseBody ResponseEntity itemOrder(@RequestBody OrderDto orderDto,
            Principal principal){
        Long orderId;
        try{
            orderId = orderService.itemOrder( orderDto, principal.getName());
        }catch(Exception e){
            System.out.println("상품구매하기 실패");
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }



    //주문내역 페이지 요청
    @GetMapping( value= {"/orderList", "/orderList/{page}" }  )
    public String orderList(@PathVariable("page")Optional<Integer> page,
                    Principal principal, Model model){

        // 페이징을 위한 코드
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,5);
        // PageRequest.of(  몇번째 페이지 ,   한페이지에 몇개   );
        // isPresent -> 값이 있냐?
        Page<OrderHistDto> orderHistDtos =
                orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", orderHistDtos);
        model.addAttribute("page",pageable.getPageNumber());
        model.addAttribute("maxPage",5);

        return "order/order";
    }


}
