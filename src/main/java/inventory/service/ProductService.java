package inventory.service;
import inventory.dao.ProductInfoDAO;
import inventory.model.ProductInfo;
import org.apache.log4j.Logger;
import inventory.dao.CategoryDAO;
import inventory.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private CategoryDAO<Category> categoryDAO;
    @Autowired
    private ProductInfoDAO<ProductInfo> productInfoDAO;
    private static final Logger log = Logger.getLogger(ProductService.class);
    public void saveproductInfo(Category category) {
        log.info("Save category: " + category.toString());
        category.setActiveFlag(1);
        category.setCreateDate(new Date().toInstant());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.save(category);
    }
    public void updateCategory(Category category) {
        log.info("=====>Update category: " + category.toString());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.update(category);
    }
    public void deleteCategory(Category category) {
        category.setActiveFlag(0);
        log.info("=====>Delete category: " + category.toString());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.update(category);
    }
    public List<Category> findCategory(String property, Object value) {
        log.info("=====>Find Category by property: " + property + " value: " + value +" start");
        return categoryDAO.findByProperty(property, value);
    }
    public List<Category> getAllCategory() {
        log.info("=====>Find All Category");
        return categoryDAO.findAll();
    }
    public Category findByIdCategory(int id) {
        log.info("=====>Find Category by id: " + id);
        return categoryDAO.findById(Category.class, id);
    }

//    PRODUCT INFO

    public void saveProductInfo(ProductInfo productInfo) {
        log.info("Save productInfo: " + productInfo.toString());
        productInfo.setActiveFlag(1);
        productInfo.setCreateDate(new Date().toInstant());
        productInfo.setUpdateDate(new Date().toInstant());
        productInfo.setImgUrl("/upload/"+productInfo.getMultipartFile().getOriginalFilename());
        productInfoDAO.save(productInfo);
    }
    public void updateProductInfo(ProductInfo productInfo) {
        log.info("=====>Update ProductInfo: " + productInfo.toString());
        productInfo.setUpdateDate(new Date().toInstant());
        productInfoDAO.update(productInfo);
    }
    public void deleteProductInfo(ProductInfo productInfo) {
        productInfo.setActiveFlag(0);
        log.info("=====>Delete productInfo: " + productInfo.toString());
        productInfo.setUpdateDate(new Date().toInstant());
        productInfoDAO.update(productInfo);
    }
    public List<ProductInfo> findProductInfo(String property, Object value) {
        log.info("=====>Find ProductInfo by property: " + property + " value: " + value +" start");
        return productInfoDAO.findByProperty(property, value);
    }
    public List<ProductInfo> getAllProductInfo() {
        log.info("=====>Find All ProductInfo");
        return productInfoDAO.findAll();
    }
    public ProductInfo findByIdProductInfo(int id) {
        log.info("=====>Find ProductInfo by id: " + id);
        return productInfoDAO.findById(ProductInfo.class, id);
    }
}
