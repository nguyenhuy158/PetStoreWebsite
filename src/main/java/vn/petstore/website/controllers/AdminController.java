package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.petstore.website.model.Admin;
import vn.petstore.website.model.Gear;
import vn.petstore.website.services.AdminService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String login(Model model) {
        System.out.println("get ne 1");
        model.addAttribute("admin", new Admin());
        return "admin/login";
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
