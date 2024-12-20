package inventory.controller;

import inventory.model.Category;
import inventory.service.ProductService;
import inventory.util.Constant;
import inventory.validate.CategoryValidator;
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
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryValidator categoryValidator;
    static final Logger log = Logger.getLogger(CategoryController.class);
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if (binder.getTarget().getClass()== Category.class) {
            binder.setValidator(categoryValidator);
        }
    }

    @RequestMapping(value = "/category/list")
    public String showCategoryList(Model model, HttpSession session, @ModelAttribute("searchForm") Category category) {
        List<Category> categories = productService.getAllCategory(category);
        if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
            model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
            session.removeAttribute(Constant.MSG_SUCCESS);
        }
        if (session.getAttribute(Constant.MSG_ERROR) != null) {
            model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
            session.removeAttribute(Constant.MSG_ERROR);
        }
        model.addAttribute("categories", categories);
        return "category-list";
    }

    @GetMapping("/category/add")
    public String add(Model model) {
        model.addAttribute("titlePage","Add  Category");
        model.addAttribute("modelForm", new Category());
        model.addAttribute("viewOnly", false);
        return "category-action";
    }
    @GetMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        log.info("Edit category with id: " + id);
        Category category = productService.findByIdCategory(id);
        if(category != null) {
            model.addAttribute("titlePage","Edit Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";
        }
        return "redirect:/category/list";
    }

    @GetMapping("/category/view/{id}")
    public String view(Model model, @PathVariable("id") int id) {
        log.info("View category with id: " + id);
        Category category = productService.findByIdCategory(id);
        if(category != null) {
            model.addAttribute("titlePage","View Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", true);
            return "category-action";
        }
        return "redirect:/category/list";
    }

    @PostMapping("/category/save")
    public String save(Model model, @ModelAttribute("modelForm") @Validated Category category, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("titlePage", category.getId() != null ? "Edit Category" : "Add Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";  // Trả lại trang nhập liệu với lỗi
        }

        if (category.getId() != null && category.getId() != 0) {
            try {
                productService.updateCategory(category);
                session.setAttribute(Constant.MSG_SUCCESS,"Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                session.setAttribute(Constant.MSG_ERROR,"Update Failed");

            }
        } else {
            try {
                productService.saveCategory(category);
                session.setAttribute(Constant.MSG_SUCCESS,"Inserted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                session.setAttribute(Constant.MSG_ERROR,"Insert Failed");
            }
        }

        return "redirect:/category/list";
    }
    @GetMapping("/category/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
        log.info("Delete category with id: " + id);
        Category category = productService.findByIdCategory(id);
        if(category != null) {
            try {
                productService.deleteCategory(category);
                session.setAttribute(Constant.MSG_SUCCESS,"Deleted Successfully");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute(Constant.MSG_ERROR,"Delete Failed");
            }
        }
        return "redirect:/category/list";
    }
}
