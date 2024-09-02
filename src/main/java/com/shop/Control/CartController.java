package com.shop.Control;

import com.shop.Dto.CartItemDto;
import com.shop.Dto.CartListDto;
import com.shop.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //장바구니 아이콘클릭시 - 내장바구니 목록 보기
    @GetMapping("/cart/list")
    public String cartList(Model model, Principal principal){
        // 스프링 시큐리티 사용시 현재 로그인한 계정명은 Principal을 통해서
        //받아 올 수 있다.
        List<CartListDto> cartListDtoList = cartService.getCartList( principal.getName());
        model.addAttribute( "cartList" ,cartListDtoList);
        return "cart/cartList";
    }


    //장바구니 버튼 클릭시 json 값 받기
    @PostMapping("/cart")
    public @ResponseBody ResponseEntity cartSave(
            @RequestBody @Valid CartItemDto cartItemDto,
            BindingResult bindingResult , Principal principal){
        if( bindingResult.hasErrors() ){// 상품번호와 수량에 문제 있다면
            StringBuilder sb = new StringBuilder(); //json응답은 문자열로
            //유효값 검사의 오류내용
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors)
                sb.append(fieldError.getDefaultMessage());
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        String userId = principal.getName(); // 로그인 계정명
        Long cartItemId=0L;
        try{
            cartItemId = cartService.addCart(cartItemDto, userId);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // 장바구닝 담기 성공 했기에 OK라고 보내기
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }
}
