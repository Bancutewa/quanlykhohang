package inventory.validate;

import org.apache.commons.io.FilenameUtils;
import inventory.model.Category;
import inventory.model.ProductInfo;
import inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ProductInfoValidator implements Validator {

    @Autowired
    private ProductService productService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductInfo productInfo = (ProductInfo) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "msg.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "multipartFile", "msg.required");
        if (productInfo.getCode()!=null) {
            List<ProductInfo> results = productService.findProductInfo("code", productInfo.getCode());
            if (productInfo.getId() != null && productInfo.getId() != 0) {
                if (results.get(0).getId() != productInfo.getId()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            }
            else{
                errors.rejectValue("code", "msg.code.exist");
            }
        }
        if (productInfo.getMultipartFile()!=null) {
            String extension = FilenameUtils.getExtension(productInfo.getMultipartFile().getOriginalFilename());
            if(!extension.equals("jpg") || !extension.equals("jpeg") || !extension.equals("png")) {
                errors.rejectValue("multipartFile", "msg.file.extension.error");
            }
        }
    }
}
