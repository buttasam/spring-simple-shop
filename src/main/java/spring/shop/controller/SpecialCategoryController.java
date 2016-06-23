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
public class SpecialCategoryController {

    @Autowired
    private ShopFacade shopFacade;


    @RequestMapping(value = "special-category", method = RequestMethod.POST)
    public String saveCategory(@Valid SpecialCategory specialCategory, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Form is not valid");

            if(specialCategory.getId() == null) {
                return "redirect:special-categories/new";
            } else {
                return "redirect:special-category/edit/" + specialCategory.getId();
            }
        }

        shopFacade.saveSpecialCategory(specialCategory);

        return "redirect:special-category/edit/" + specialCategory.getId();
    }


    @RequestMapping(value = "special-category/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("specialCategory", shopFacade.getSpecialCategoryById(id));

        return "specialCategoryEdit";
    }


    @RequestMapping(value = "special-category/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            shopFacade.deleteSpecialCategoryById(id);
        } catch(Exception ex) {
            redirectAttributes.addFlashAttribute("message", "Special category cannot be deleted");
        }

        return "redirect:/categories";
    }

    @RequestMapping("special-categories/new")
    public String newCategory(Model model) {

        model.addAttribute("specialCategory", new SpecialCategory());

        return "specialCategoryEdit";
    }

}
