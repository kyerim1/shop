package com.shop.Control;

import com.shop.Dto.MemberForm;
import com.shop.Dto.MemberLogin;
import com.shop.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지 요청
    @GetMapping("/signIn")
    public String loginPage(Model model){
        model.addAttribute("memberLogin",new MemberLogin());
        return "member/login";
    }

    // 회원가입 페이지 요청
    @GetMapping("/signUp")
    public String joinPage(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }

}
