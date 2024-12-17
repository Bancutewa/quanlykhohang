package inventory.validate;

import inventory.model.User;
import inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class LoginValidator implements Validator {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "msg.required");

        if (!StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword())) {
            // Kiểm tra người dùng có tồn tại không
            List<User> users = userService.findByProperty("userName", user.getUserName());

            if (users.isEmpty()) {
                // Nếu người dùng không tồn tại
                errors.rejectValue("userName", "msg.wrong.username");
            } else {
                // Nếu người dùng tồn tại, kiểm tra mật khẩu
                if (!users.get(0).getPassword().equals(user.getPassword())) {
                    // Nếu mật khẩu sai
                    errors.rejectValue("password", "msg.wrong.password");
                }
            }
        }
    }

}
