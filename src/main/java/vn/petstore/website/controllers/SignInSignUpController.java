package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class SignInSignUpController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUp(Model model) {
        System.out.println("get sign up");
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        System.out.println("add user post");

        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return "redirect:sign-up?fail";
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        User byUserName = userRepository.findByUsername(user.getUsername());
        if (byUserName == null) {
            userRepository.save(user);
        } else {
            return "redirect:sign-up?fail";
        }

        return "redirect:login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute User user) {
        String username = user.getUsername();
        User byUserName = userRepository.findByUsername(username);

        if (byUserName != null) {
            boolean matches = new BCryptPasswordEncoder().matches(user.getPassword(), byUserName.getPassword());
            if (matches) {
                return "index";
            }
        }
        return "redirect:sign-up";
    }

}
