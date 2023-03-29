package vn.petstore.website.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.petstore.website.model.CustomUserDetails;
import vn.petstore.website.model.Product;
import vn.petstore.website.model.User;
import vn.petstore.website.services.CartService;
import vn.petstore.website.services.CustomUserDetailService;
import vn.petstore.website.services.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model, @RequestParam("id") Optional<Long> productId) {
        // add product to cart
        System.out.println("dat hang productId");
        System.out.println(productId);
        if (productId.isPresent()) {
            cartService.addProductToCart(productId.get());
        }

        System.out.println("username");
        System.out.println(userService.getCurrentUser().toString());

        // return list product in cart
        List<Product> products = cartService.getCart(userService.getCurrentUserId());
        model.addAttribute("products", products);

        products.stream().forEach(System.out::println);
        return "cart";
    }


}
