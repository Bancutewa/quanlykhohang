package inventory.controller;

import inventory.model.User;
import inventory.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
public class UserController {
    @GetMapping("/index")
    public String hello(HttpSession session) {
        Object userInfo = session.getAttribute("userInfo");
        System.out.println("Session userInfo: " + userInfo);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            System.out.println(attrName + ": " + session.getAttribute(attrName));
        }
        return "index";  // Trả về index.jsp
    }
    @Autowired
    private UserService userService;
}