package inventory.service;
import inventory.dao.ProductInfoDAO;
import inventory.model.ProductInfo;
import inventory.util.ConfigLoader;
import org.apache.log4j.Logger;
import inventory.dao.CategoryDAO;
import inventory.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private CategoryDAO<Category> categoryDAO;
    @Autowired
    private ProductInfoDAO<ProductInfo> productInfoDAO;
    private static final Logger log = Logger.getLogger(ProductService.class);
    public void saveCategory(Category category) throws Exception {
        log.info("Save category: " + category.toString());
        category.setActiveFlag(1);
        category.setCreateDate(new Date().toInstant());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.save(category);
    }
    public void updateCategory(Category category) throws Exception {
        log.info("=====>Update category: " + category.toString());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.update(category);
    }
    public void deleteCategory(Category category) throws Exception {
        category.setActiveFlag(0);
        log.info("=====>Delete category: " + category.toString());
        category.setUpdateDate(new Date().toInstant());
        categoryDAO.update(category);
    }
    public List<Category> findCategory(String property, Object value) {
        log.info("=====>Find Category by property: " + property + " value: " + value +" start");
        return categoryDAO.findByProperty(property, value);
    }
    public List<Category>  getAllCategory(Category category) {
        log.info("=====>Find All Category");
        StringBuilder queryStr = new StringBuilder();
        Map<String, Object> mapParams  = new HashMap<>();
        if (category!=null) {
            if (category.getId()!=null && category.getId()!=0) {
                queryStr.append(" and model.id=:id");
                mapParams.put("id", category.getId());
            }
            if (category.getCode()!=null && !StringUtils.isEmpty(category.getCode())) {
                queryStr.append(" and model.code=:code");
                mapParams.put("code", category.getCode());
            }
            if (category.getName()!=null && !StringUtils.isEmpty(category.getName())) {
                queryStr.append(" and model.name=:name");
                mapParams.put("name", category.getName());
            }

        }
        return categoryDAO.findAll(queryStr.toString(), mapParams);
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
        processUploadFile(productInfo.getMultipartFile());
        productInfo.setImgUrl("/upload/"+System.currentTimeMillis()+"_"+productInfo.getMultipartFile().getOriginalFilename());
        productInfoDAO.save(productInfo);
    }
    public void updateProductInfo(ProductInfo productInfo) {
        log.info("=====>Update ProductInfo: " + productInfo.toString());
        processUploadFile(productInfo.getMultipartFile());
        if (productInfo.getMultipartFile()!=null) {
            productInfo.setImgUrl("/upload/"+System.currentTimeMillis()+"_"+productInfo.getMultipartFile().getOriginalFilename());
        }
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
    private void processUploadFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
        if(multipartFile!=null) {
            File dir = new File(ConfigLoader.getInstance().getValue("upload.location"));
            if(!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = System.currentTimeMillis()+"_"+multipartFile.getOriginalFilename();
            File file = new File(ConfigLoader.getInstance().getValue("upload.location"), fileName);
            multipartFile.transferTo(file);
        }
    }
}
