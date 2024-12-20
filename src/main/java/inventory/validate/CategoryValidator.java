package inventory.validate;

import inventory.model.Category;
import inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class CategoryValidator implements Validator {

    @Autowired
    private ProductService productService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Category.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "msg.required");
        if (category.getCode()!=null && !category.getCode().isEmpty()) {
            List<Category> results = productService.findCategory("code", category.getCode());
            if (category.getId() != null && category.getId() != 0) {
                if (results.get(0).getId() != category.getId()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            }
            else{
                errors.rejectValue("code", "msg.code.exist");
            }
        }

    }
}
