package inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    // Xử lý yêu cầu /hello
    @GetMapping("/hello")
    public String hello() {
        return "index";  // Trả về index.jsp
    }

    // Xử lý yêu cầu /
    @RequestMapping("/")
    public String home() {
        return "index";  // Trả về index.jsp
    }
}
