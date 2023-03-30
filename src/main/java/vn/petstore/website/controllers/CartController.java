package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.petstore.website.model.CartDto;
import vn.petstore.website.services.CartService;
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

    @GetMapping(value = { "/cart", "/cartBack" })
    public String addProduct(Model model) {
        List<CartDto> cartDtos = cartService.getCart();
        Long subtotal = cartService.getSubtotal();
        Double grandTotal = cartService.getGrandTotal();
        Double tax = cartService.getTax();
        model.addAttribute("cartDtos", cartDtos);
        model.addAttribute("subTotal", subtotal);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("tax", tax);

        cartDtos.stream().forEach(System.out::println);
        return "cart";
    }

    @GetMapping("/cart/addProduct")
    public String addProduct(Model model, @RequestParam("id") Optional<Long> productId) {
        // add product to cart
        System.out.println("add productId");
        System.out.println(productId);
        if (productId.isPresent()) {
            cartService.addProductToCart(productId.get());
        }

        return "redirect:/cartBack";
    }

    @GetMapping("/cart/incrementProduct")
    public String incrementProduct(Model model, @RequestParam("id") Optional<Long> productId) {
        // add product to cart
        System.out.println("incrementProduct");
        System.out.println(productId);
        if (productId.isPresent()) {
            cartService.incrementProductToCart(productId.get());
        }

        return "redirect:/cartBack";
    }

    @GetMapping("/cart/decrementProduct")
    public String decrementProduct(Model model, @RequestParam("id") Optional<Long> productId) {
        // add product to cart
        System.out.println("decrementProduct");
        System.out.println(productId);
        if (productId.isPresent()) {
            cartService.decrementProductToCart(productId.get());
        }

        return "redirect:/cartBack";
    }

    @GetMapping("/cart/removeProduct")
    public String removeProduct(Model model, @RequestParam("id") Optional<Long> productId) {
        // remove product from cart
        System.out.println("remove productId");
        System.out.println(productId);
        if (productId.isPresent()) {
            cartService.removeProductToCart(productId.get());
        }

        return "redirect:/cartBack";
    }
}
