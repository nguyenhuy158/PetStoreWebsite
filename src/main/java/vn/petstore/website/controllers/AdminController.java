package vn.petstore.website.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.dto.PaginatedProductResponse;
import vn.petstore.website.model.Admin;
import vn.petstore.website.model.Gear;
import vn.petstore.website.services.AdminService;
import vn.petstore.website.services.ProductService;
import vn.petstore.website.services.UserService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String login(
            @RequestParam(name = "keyword", defaultValue = "") Optional<String> keyword,
            @RequestParam(defaultValue = "0") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            Model model) {
        model.asMap().clear();
        model.addAttribute("admin", new Admin());
        model.addAttribute("isAdmin", userService.isAdmin());

        String sortField = sort[0];
        String sortDirection = sort[1];
        Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Order order = new Order(direction, sortField);

        PaginatedProductResponse paginatedProductResponse;
        if (keyword.isPresent()) {
            paginatedProductResponse = productService
                    .filterBooks(keyword.get(), PageRequest.of(currentPage, pageSize, Sort.by(order)));
        } else {
            paginatedProductResponse = productService
                    .readProducts(PageRequest.of(currentPage, pageSize, Sort.by(order)));
        }
        model.addAttribute("paginatedProductResponse", paginatedProductResponse);
        model.addAttribute("currentNumberProduct", Math.min(paginatedProductResponse.getNumberOfItems(), (currentPage
                + 1) * pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword.get());
        model.addAttribute("pageSize", pageSize + 1);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        int totalPages = paginatedProductResponse.getNumberOfPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/index";
    }

    @PostMapping("/admin/login")
    public String isAdmin(@ModelAttribute Admin admin) {
        System.out.println("post ne");

        if (adminService.isValid(admin)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin?fail";
        }
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("product", new Gear());
        return "admin/dashboard";
    }

}
