package vn.petstore.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(name = "/add-user")
    public void addUser(@ModelAttribute User user) {
        userRepository.findAll().forEach(System.out::println);
        userRepository.save(user);

    }
}
