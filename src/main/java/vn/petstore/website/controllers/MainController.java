package vn.petstore.website.controllers;

import static vn.petstore.website.constances.Const.PRODUCT_LIMIT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.model.Product;
import vn.petstore.website.services.ProductService;
import vn.petstore.website.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping(value = { "/", "/home" })
    public String index(Model model) {
        // hander if current user is admin
        if (userService.isAdmin()) {
            return "redirect:/admin";
        }

        // hander if current user not admin
        List<Product> products = productService.getAllProducts(PRODUCT_LIMIT);

        model.asMap().clear();
        model.addAttribute("pageTitle", "Pet Store - Home");
        model.addAttribute("products", products);
        model.addAttribute("isLogout", userService.getCurrentUser());
        model.addAttribute("isLogin", userService.isLogin());

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("isLogin", userService.isLogin());

        System.out.println("/about");
        System.out.println(userService.isLogin());

        return "about";
    }

    @GetMapping("/user-info")
    public String userInfo(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "user-info";
    }
}
