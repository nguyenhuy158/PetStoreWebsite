package vn.petstore.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.petstore.website.services.CartService;

@Controller
public class CheckoutController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = { "/checkout" }, method = RequestMethod.POST)
    public String checkout(Model model) {
        if (cartService.isHasProduct()) {

            cartService.checkout();

            return "redirect:history";
        } else {
            return "redirect:cart";
        }
    }

}
