package inventory.controller;

import inventory.model.User;
import inventory.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @GetMapping("/index")
    public String hello() {
        return "index";  // Trả về index.jsp
    }
    @Autowired
    private UserService userService;
}