package vn.petstore.website.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.petstore.website.model.Admin;
import vn.petstore.website.model.Product;
import vn.petstore.website.services.AdminService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String login(Model model) {
        System.out.println("get ne");
        model.addAttribute("admin", new Admin());
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String isAdmin(@ModelAttribute Admin admin) {
        System.out.println("post ne");
        System.out.println(admin.toString());
        System.out.println(adminService.isValid(admin));

        if (adminService.isValid(admin)) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/dashboard";
    }

    @GetMapping("/admin/fail")
    public String fail(Model model) {
        return "admin/fail";
    }
}
