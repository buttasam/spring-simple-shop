package spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.shop.facede.ShopFacade;

@Controller
public class CategoryController {

    @Autowired
    private ShopFacade shopFacade;


    @RequestMapping("/categories")
    public String listCategories(Model model) {

        return "categories";
    }


    @RequestMapping("categories/new")
    public String newCategory(Model model) {

        return "categoryEdit";
    }

}
