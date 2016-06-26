package spring.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.shop.entity.Product;
import spring.shop.facede.ShopFacade;
import spring.shop.repositories.ProductRepository;
import spring.shop.rest.RestProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger log = Logger.getLogger(RestController.class.getName());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopFacade shopFacade;

    @RequestMapping("rest/products")
    public List<RestProduct> restProducts() {

        List<Product> products = productRepository.findAll();
        List<RestProduct> resultList = new ArrayList<>();

        products.stream().forEach(p -> resultList.add(createRestProduct(p.getId())));

        log.info("Array size is: " + resultList.size());

        return resultList;
    }


    @RequestMapping("rest/product/{id}")
    public RestProduct restOneProduct(@PathVariable Integer id) {

        return createRestProduct(id);
    }


    private RestProduct createRestProduct(Integer productId) {
        Product product = productRepository.findOne(productId);

        List<String> categories = new ArrayList<>();
        product.getCategories().forEach(c -> categories.add(c.getName()));

        RestProduct restProduct = new RestProduct(product.getDescription(), product.getPrice(), categories);

        return restProduct;
    }


    @RequestMapping(value = "rest/product", method = RequestMethod.POST)
    public String createRestProduct(@RequestBody String name) {
        Product product = new Product(name);

        return shopFacade.saveProduct(product).getDescription();
    }

}
