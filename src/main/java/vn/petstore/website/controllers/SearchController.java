package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.ProductPagingRepository;
import vn.petstore.website.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static vn.petstore.website.constances.Const.PRODUCT_LIMIT;
import static vn.petstore.website.constances.Const.PRODUCT_PAGE;

@Controller
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductPagingRepository productPagingRepository;

    //    @GetMapping("/search")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(PRODUCT_PAGE);
        int pageSize = size.orElse(PRODUCT_LIMIT);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "search";
//        Pageable firstPageWithTwoElements = PageRequest.of(0,
//                PRODUCT_LIMIT,
//                Sort.by("price").descending());
//        Page<Product> productPage = productPagingRepository.findAll(firstPageWithTwoElements);
//        List<Product> content = productPage.getContent();
//        model.addAttribute("products", content);
//
//        int totalPages = productPage.getTotalPages();
//        model.addAttribute("totalPages", totalPages);
    }
}
