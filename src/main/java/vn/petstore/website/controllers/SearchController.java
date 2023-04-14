package vn.petstore.website.controllers;

import static vn.petstore.website.constances.Const.PRODUCT_ID;
import static vn.petstore.website.constances.Const.PRODUCT_LIMIT;
import static vn.petstore.website.constances.Const.PRODUCT_PAGE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.model.Cart;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.ProductPagingRepository;
import vn.petstore.website.services.ProductService;
import vn.petstore.website.services.UserService;

@Controller
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    ProductPagingRepository productPagingRepository;

    // @GetMapping("/search")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        model.addAttribute("isLogin", userService.isLogin());

        int currentPage = page.orElse(PRODUCT_PAGE);
        int pageSize = size.orElse(PRODUCT_LIMIT);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "search";
    }

    @RequestMapping(value = "/product-detail", method = RequestMethod.GET)
    public String productDetail(Model model, @RequestParam("id") Optional<Long> id) {
        Long productId = id.orElse(PRODUCT_ID);

        System.out.println("productId");
        System.out.println(productId);

        Product productById = productService.getProductById(productId);

        model.addAttribute("product", productById);
        model.addAttribute("cartOnlyQuantity", new Cart());
        model.addAttribute("isLogin", userService.isLogin());

        return "product-detail";
    }
}
