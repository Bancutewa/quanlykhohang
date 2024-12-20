package inventory.controller;

import inventory.model.*;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @PostMapping("/processLogin")
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
        UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
        List<Menu> menuList = new ArrayList<>();
        Role role = userRole.getRole();
        List<Menu> menuChildList = new ArrayList<>();

        // Duyệt qua các quyền của role để tìm các menu con
        for (Object obj : role.getAuths()) {
            Auth auth = (Auth) obj;
            Menu menu = auth.getMenu();

            // Thêm vào menuList hoặc menuChildList tùy thuộc vào ParentId
            if (menu.getParentId() == 0 && menu.getOrderIndex() != -1 && auth.getPermission() == 1 && auth.getActiveFlag() == 1) {
                menu.setIdMenu(menu.getUrl().replace("/", "+Id"));
                menuList.add(menu);  // Menu cha
            } else if (menu.getParentId() != 0 && menu.getOrderIndex() != -1 && auth.getPermission() == 1 && auth.getActiveFlag() == 1) {
                menu.setIdMenu(menu.getUrl().replace("/", "+Id"));
                menuChildList.add(menu);  // Menu con
            }
        }

        // Gán menu con cho menu cha
        for (Menu menu : menuList) {
            List<Menu> childList = new ArrayList<>();
            for (Menu childMenu : menuChildList) {
                if (childMenu.getParentId() == menu.getId()) {
                    childList.add(childMenu);
                }
            }
            menu.setChild(childList);
        }

        // Sắp xếp menu và menu con
        sortMenu(menuList);
        for (Menu menu : menuList) {
            sortMenu(menu.getChild());
        }

        // Lưu menu vào session
        session.setAttribute(Constant.MENU_SESSION, menuList);

        // Lưu thông tin người dùng vào session
        session.setAttribute(Constant.USER_INFO, user);

        // Chuyển hướng đến trang chính sau khi đăng nhập thành công
        return "redirect:/index";
    }

    public void sortMenu(List<Menu> menus) {
        menus.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getOrderIndex() - o2.getOrderIndex();
            }
        });
    }
}