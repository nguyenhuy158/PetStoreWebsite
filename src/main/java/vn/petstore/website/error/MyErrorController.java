package vn.petstore.website.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import vn.petstore.website.services.UserService;

@Controller
public class MyErrorController implements ErrorController {

    @Autowired
    UserService userDetailsServiceImpl;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("isLogin", userDetailsServiceImpl.isLogin());

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("pageTitle", "404");
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("pageTitle", "500");
                return "error-500";
            } else if (statusCode == HttpStatus.CONFLICT.value()) {
                model.addAttribute("pageTitle", "409");
                return "error";
            }
        }
        model.addAttribute("pageTitle", "Error");
        return "error";

    }
}
