package com.shop.Control;

import com.shop.Dto.ItemForm;
import com.shop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainControl {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/admin")
    public String adminHome(Model model){
        List<ItemForm> itemFormList =itemService.getAdminItemPage();
        model.addAttribute("itemFormList",itemFormList);
        return "admin/index";
    }
}
