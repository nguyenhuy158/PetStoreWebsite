package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        User byUserName = userRepository.findByUsername(user.getUsername());
        if (byUserName == null) {
            userRepository.save(user);
        } else {

            System.out.println(byUserName.toString());
        }

        return "login";
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
        return "sign-up";
    }

}
