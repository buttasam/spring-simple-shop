package spring.shop.controller;

import spring.shop.entity.Product;
import spring.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("rest/products")
    public List<Product> restProducts() {

        return productRepository.findAll();
    }


    @RequestMapping("rest/product/{id}")
    public Product restOneProduct(@PathVariable Integer id) {

        return productRepository.findOne(id);
    }

}
