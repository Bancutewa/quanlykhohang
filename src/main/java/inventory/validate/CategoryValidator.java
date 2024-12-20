package inventory.validate;

import inventory.controller.CategoryController;
import inventory.model.Category;
import inventory.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;
@Component
public class CategoryValidator implements Validator {
    static final Logger log = Logger.getLogger(CategoryController.class);

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Category.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        // Kiểm tra các trường bắt buộc
        ValidationUtils.rejectIfEmpty(errors, "code", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "msg.required");

        // Kiểm tra code chỉ khi code không rỗng
        if (category.getCode() != null) {
            List<Category> results = productService.findCategory("code", category.getCode());

            // Nếu là sửa category (category.getId() != null), kiểm tra trùng code
            if (category.getId() != null && category.getId() != 0) {
                log.info("Checking category with code: " + category.getCode());

                if (results != null && !results.isEmpty() && results.get(0).getId() != category.getId()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            } else {
                // Nếu là thêm mới (không có id hoặc id = 0)
                if (results != null && !results.isEmpty()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            }
        }
    }
}
