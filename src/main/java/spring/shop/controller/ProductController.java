package spring.shop.controller;

import org.springframework.web.bind.annotation.RequestParam;
import spring.shop.entity.Category;
import spring.shop.entity.Product;
import spring.shop.facede.ShopFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ShopFacade shopFacade;


    private Product product;

    @RequestMapping("product/new")
    public String newProduct(Model model) {

        model.addAttribute("product", new Product());
        return "productEdit";
    }


    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        shopFacade.saveProduct(product);
        return "redirect:product/edit/" + product.getId();
    }


    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        product = shopFacade.getProductById(id);

        model.addAttribute("product", product);

        return "productDetail";
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("products", shopFacade.getAllProducts());

        return "products";
    }


    @RequestMapping(value = "product/delete/{id}")
    public String delete(@PathVariable Integer id) {
        shopFacade.deleteProductById(id);

        return "redirect:/products";
    }

    @RequestMapping(value = "product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = shopFacade.getProductById(id);
        List<Category> addCategories = shopFacade.getProductHasNotCategories(product);

        model.addAttribute("product", shopFacade.getProductById(id));
        model.addAttribute("addCategories", addCategories);

        return "productEdit";
    }


    @RequestMapping(value = "products/add-product-to-category")
    public String addProductToCategory(@RequestParam(value = "productId") Integer productId, @RequestParam(value = "categoryId") Integer categoryId) {

        Product product = shopFacade.getProductById(productId);
        Category category = shopFacade.getCategoryById(categoryId);

        shopFacade.addProductToCategory(product, category);
        return "redirect:/product/edit/" + product.getId();
    }


    @RequestMapping(value = "products/remove-product-from-category")
    public String removeProductFromCategory(@RequestParam(value = "productId") Integer productId, @RequestParam(value = "categoryId") Integer categoryId) {
        Product product = shopFacade.getProductById(productId);
        Category category = shopFacade.getCategoryById(categoryId);

        shopFacade.removeProductFromCategory(product, category);
        return "redirect:/product/edit/" + product.getId();
    }
}
