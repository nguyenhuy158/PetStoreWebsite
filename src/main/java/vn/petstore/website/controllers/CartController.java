package vn.petstore.website.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/cart")
    public String cart(Model model, @RequestParam("id") Optional<Long> id) {

        System.out.println("dat hang id");
        System.out.println(id);
        return "cart";
    }


}
