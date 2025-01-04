package inventory.controller;

import inventory.model.Category;
import inventory.model.ProductInfo;
import inventory.model.ProductInfo;
import inventory.service.ProductService;
import inventory.util.Constant;
import inventory.validate.ProductInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductInfoController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInfoValidator productInfoValidator;
    static final Logger log = Logger.getLogger(ProductInfoController.class);
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if (binder.getTarget().getClass()== ProductInfo.class) {
//            binder.setValidator(productInfoValidator);
        }
    }

    @RequestMapping(value = "/productInfo/list")
    public String showProductInfoList(Model model, HttpSession session, @ModelAttribute("searchForm") ProductInfo productInfo) {
        List<ProductInfo> categories = productService.getAllProductInfo(productInfo);
        if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
            model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
            session.removeAttribute(Constant.MSG_SUCCESS);
        }
        if (session.getAttribute(Constant.MSG_ERROR) != null) {
            model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
            session.removeAttribute(Constant.MSG_ERROR);
        }
        model.addAttribute("categories", categories);
        return "product-info/list";
    }
    @GetMapping("/productInfo/add")
    public String add(Model model) {
        model.addAttribute("titlePage", "Add ProductInfo");
        model.addAttribute("modelForm", new ProductInfo());

        // Lấy danh sách Category từ service
        List<Category> categories = productService.getAllCategory(null);

        // Chuyển danh sách Category thành Map<String, String>
        Map<String, String> mapCategory = new HashMap<>();
        for (Category category : categories) {
            mapCategory.put(String.valueOf(category.getId()), category.getName());
        }

        model.addAttribute("mapCategories", mapCategory);
        model.addAttribute("viewOnly", false);

        return "productInfo-action"; // Trả về view 'productInfo-action.jsp'
    }

    @GetMapping("/productInfo/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        log.info("Edit productInfo with id: " + id);
        ProductInfo productInfo = productService.findByIdProductInfo(id);
        if(productInfo != null) {
            List<ProductInfo> categories = productService.getAllProductInfo(null);
            Map<String, String> mapProductInfo = new HashMap<>();
            for (ProductInfo ProductInfo : categories) {
                mapProductInfo.put(String.valueOf(ProductInfo.getId()), ProductInfo.getName());
            }
            model.addAttribute("mapCategories", mapProductInfo);
            model.addAttribute("titlePage","Edit ProductInfo");
            model.addAttribute("modelForm", productInfo);
            model.addAttribute("viewOnly", false);
            return "productInfo-action";
        }
        return "redirect:/product-info/list";
    }

    @GetMapping("/productInfo/view/{id}")
    public String view(Model model, @PathVariable("id") int id) {
        log.info("View productInfo with id: " + id);
        ProductInfo productInfo = productService.findByIdProductInfo(id);
        if(productInfo != null) {
            model.addAttribute("titlePage","View ProductInfo");
            model.addAttribute("modelForm", productInfo);
            model.addAttribute("viewOnly", true);
            return "productInfo-action";
        }
        return "redirect:/product-info/list";
    }

    @PostMapping("/productInfo/save")
    public String save(Model model, @ModelAttribute("modelForm") @Validated ProductInfo productInfo, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("titlePage", productInfo.getId() != null ? "Edit ProductInfo" : "Add ProductInfo");
            List<ProductInfo> categories = productService.getAllProductInfo(null);
            Map<String, String> mapProductInfo = new HashMap<>();
            for (ProductInfo ProductInfo : categories) {
                mapProductInfo.put(String.valueOf(ProductInfo.getId()), ProductInfo.getName());
            }
            model.addAttribute("mapCategories", mapProductInfo);

            model.addAttribute("modelForm", productInfo);
            model.addAttribute("viewOnly", false);
            return "productInfo-action";  // Trả lại trang nhập liệu với lỗi
        }


        if (productInfo.getId() != null && productInfo.getId() != 0) {
            try {
                productService.updateProductInfo(productInfo);
                session.setAttribute(Constant.MSG_SUCCESS,"Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                session.setAttribute(Constant.MSG_ERROR,"Update Failed");

            }
        } else {
            try {
                productService.saveProductInfo(productInfo);
                session.setAttribute(Constant.MSG_SUCCESS,"Inserted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                session.setAttribute(Constant.MSG_ERROR,"Insert Failed");
            }
        }

        return "redirect:/product-info/list";
    }
    @GetMapping("/productInfo/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
        log.info("Delete productInfo with id: " + id);
        ProductInfo productInfo = productService.findByIdProductInfo(id);
        if(productInfo != null) {
            try {
                productService.deleteProductInfo(productInfo);
                session.setAttribute(Constant.MSG_SUCCESS,"Deleted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute(Constant.MSG_ERROR,"Delete Failed");
            }
        }
        return "redirect:/product-info/list";
    }
}
