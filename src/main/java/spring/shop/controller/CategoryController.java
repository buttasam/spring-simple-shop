package spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.shop.entity.Category;
import spring.shop.facede.ShopFacade;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ShopFacade shopFacade;


    @RequestMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = shopFacade.getAllCategories();
        model.addAttribute("categories", categories);

        return "categories";
    }


    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String saveCategory(Category category) {
        shopFacade.saveCategory(category);

        return "redirect:category/edit/" + category.getId();
    }


    @RequestMapping(value = "category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("category", shopFacade.getCategoryById(id));

        return "categoryEdit";
    }


    @RequestMapping(value = "category/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        shopFacade.deleteCategoryById(id);

        return "redirect:/categories";
    }

    @RequestMapping("categories/new")
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());

        return "categoryEdit";
    }

}
