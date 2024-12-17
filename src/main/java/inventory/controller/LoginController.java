package inventory.controller;

import inventory.model.User;
import inventory.service.UserService;
import inventory.util.Constant;
import inventory.validate.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginValidator loginValidator;

    // Phương thức để khởi tạo validator cho User
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) return;
        if (binder.getTarget().getClass() == User.class) {
            binder.setValidator(loginValidator);
        }
    }

    // Phương thức GET để hiển thị form đăng nhập
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new User());
        return "login/login";
    }

    // Phương thức POST để xử lý đăng nhập
    @PostMapping("processLogin")
    public String processLogin(Model model,
                               @ModelAttribute("loginForm") @Validated User users,
                               BindingResult result,
                               HttpSession session) {
        // Nếu có lỗi khi kiểm tra hợp lệ, trả lại trang login
        if (result.hasErrors()) {
            return "login/login";
        }

        // Kiểm tra thông tin người dùng
        User user = userService.findByProperty("userName", users.getUserName()).get(0);

        // Lưu thông tin người dùng vào session
        session.setAttribute(Constant.USER_INFO, user);

        // Thêm user vào model để sử dụng trong JSP
        model.addAttribute("user_info", user);

        // Kiểm tra session sau khi lưu
        Object userInfo = session.getAttribute(Constant.USER_INFO);
        System.out.println("User info in session: " + userInfo);

        // Chuyển hướng đến trang chính sau khi đăng nhập thành công
        return "redirect:/index"; // Lúc này bạn cần đảm bảo trang /index cũng nhận được user_info từ model
    }


}