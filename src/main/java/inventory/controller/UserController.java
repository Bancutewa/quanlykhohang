package inventory.controller;

import inventory.model.User;
import inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setUserName("john_doe");
        newUser.setPassword("password123");
        newUser.setName("John Doe");
        newUser.setEmail("johndoe@example.com");
        newUser.setActiveFlag(1);
        userService.addUser(newUser);
        return "userAdded";
    }
}