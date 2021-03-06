package spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.shop.entity.Category;
import spring.shop.entity.SpecialCategory;
import spring.shop.facede.ShopFacade;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private ShopFacade shopFacade;


    @RequestMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = shopFacade.getAllCategories();
        List<SpecialCategory> specialCategories = shopFacade.getAllSpecialCategories();


        System.out.println(categories.size());
        model.addAttribute("categories", categories);
        model.addAttribute("specialCategories", specialCategories);

        return "categories";
    }


    @RequestMapping(value = "category", method = RequestMethod.POST)
    public String saveCategory(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Form is not valid");

            if(category.getId() == null) {
                return "redirect:categories/new";
            } else {
                return "redirect:category/edit/" + category.getId();
            }
        }

        shopFacade.saveCategory(category);

        return "redirect:category/edit/" + category.getId();
    }


    @RequestMapping(value = "category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("category", shopFacade.getGeneralCategoryById(id));

        return "categoryEdit";
    }


    @RequestMapping(value = "category/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            shopFacade.deleteCategoryById(id);
        } catch(Exception ex) {
            redirectAttributes.addFlashAttribute("message", "Category cannot be deleted");
        }

        return "redirect:/categories";
    }

    @RequestMapping("categories/new")
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());

        return "categoryEdit";
    }

}
