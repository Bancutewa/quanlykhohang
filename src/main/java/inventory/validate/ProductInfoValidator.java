package inventory.validate;

import inventory.controller.CategoryController;
import inventory.model.ProductInfo;
import inventory.service.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ProductInfoValidator implements Validator {
    static final Logger log = Logger.getLogger(CategoryController.class);

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ProductInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductInfo productInfo = (ProductInfo) target;

        // Kiểm tra các trường bắt buộc
        ValidationUtils.rejectIfEmpty(errors, "code", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "description", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "multipartFile", "msg.required");
        // Kiểm tra code chỉ khi code không rỗng
        if (productInfo.getCode() != null) {
            List<ProductInfo> results = productService.findProductInfo("code", productInfo.getCode());

            // Nếu là sửa category (category.getId() != null), kiểm tra trùng code
            if (productInfo.getId() != null && productInfo.getId() != 0) {
                log.info("Checking productInfo with code: " + productInfo.getCode());

                if (results != null && !results.isEmpty() && results.get(0).getId() != productInfo.getId()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            } else {
                // Nếu là thêm mới (không có id hoặc id = 0)
                if (results != null && !results.isEmpty()) {
                    errors.rejectValue("code", "msg.code.exist");
                }
            }
        }
        if (productInfo.getMultipartFile() != null) {
            String extension = FilenameUtils.getExtension(productInfo.getMultipartFile().getOriginalFilename());
            if(!extension.equals("jpg") || !extension.equals("jpeg") || !extension.equals("png")) {
                errors.rejectValue("multipartFile", "msg.file.extension.error");
            }
        }
    }
}
