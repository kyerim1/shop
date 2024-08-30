package com.shop.Control;

import com.shop.Dto.ItemForm;
import com.shop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    //  요청주소 - /item/OUTER   중에서 /item은 클래스 위
    // RequestMapping이 받아주고  /OUTER를 값으로 받아준다.
    @GetMapping("/{category}")
    public String itemList(@PathVariable("category") String category,
                           Model model){
        List<ItemForm> itemFormList =
                itemService.getItemList(category);
        model.addAttribute("itemList", itemFormList);

        return "item/list";
    }

    //상품 상세 페이지 요청   /item/detail/상품번호
    @GetMapping("/detail/{itemId}")
    public String detail(@PathVariable("itemId") Long itemId,
                         Model model){
        ItemForm itemForm = itemService.getItem( itemId);
        model.addAttribute("item", itemForm);
        return "item/detail";
    }
}
