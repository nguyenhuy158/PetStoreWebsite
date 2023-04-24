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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.dto.PaginatedProductResponse;
import vn.petstore.website.dto.PaginatedUserResponse;
import vn.petstore.website.model.Admin;
import vn.petstore.website.model.Gear;
import vn.petstore.website.model.Product;
import vn.petstore.website.services.AdminService;
import vn.petstore.website.services.ProductService;
import vn.petstore.website.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping(value = { "/", "" })
    public String login(Model model) {

        model.asMap().clear();
        model.addAttribute("tabHome", true);
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("admin", new Admin());
        model.addAttribute("isAdmin", userService.isAdmin());

        System.out.println("user is admin" + userService.getUserById(userService.getCurrentUserId()));
        return "admin/index";
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(name = "keyword", defaultValue = "") Optional<String> keyword,
            @RequestParam(defaultValue = "0") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            Model model) {

        model.asMap().clear();
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("pageTitle", "Products Manager");
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

        // done
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String showNewForm(Model model) {
        model.addAttribute("isLogin", userService.isLogin());

        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add Product");
        return "admin/product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("message", "The product has been saved successfully.");
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("pageTitle", "Edit Product");
        model.addAttribute("admin", new Admin());
        model.addAttribute("isAdmin", userService.isAdmin());

        try {
            Product product = productService.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit product (ID: " + id + ")");

            return "admin/product_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/products";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.delete(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/users")
    public String users(
            @RequestParam(name = "keyword", defaultValue = "") Optional<String> keyword,
            @RequestParam(defaultValue = "0") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            Model model) {

        model.asMap().clear();
        model.addAttribute("isLogin", userService.isLogin());
        model.addAttribute("pageTitle", "User Manager");
        model.addAttribute("admin", new Admin());
        model.addAttribute("isAdmin", userService.isAdmin());

        String sortField = sort[0];
        String sortDirection = sort[1];
        Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Order order = new Order(direction, sortField);

        PaginatedUserResponse paginatedUserResponse;
        if (keyword.isPresent()) {
            paginatedUserResponse = userService
                    .filterBooks(keyword.get(), PageRequest.of(currentPage, pageSize,
                            Sort.by(order)));
        } else {
            paginatedUserResponse = userService
                    .readProducts(PageRequest.of(currentPage, pageSize, Sort.by(order)));
        }
        model.addAttribute("paginatedUserResponse", paginatedUserResponse);
        model.addAttribute("currentNumberProduct",
                Math.min(paginatedUserResponse.getNumberOfItems(), (currentPage
                        + 1) * pageSize));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword.get());
        model.addAttribute("pageSize", pageSize + 1);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

        int totalPages = paginatedUserResponse.getNumberOfPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,
                    totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        // done
        return "admin/users";
    }

    // don't use
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
