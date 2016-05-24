package spring.shop.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import spring.shop.entity.Category;
import spring.shop.entity.Product;
import spring.shop.repositories.CategoryRepository;
import spring.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provides basic initialization of persistence
 */
@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product product1 = new Product();
        product1.setDescription("Shirt");
        product1.setPrice(250.0);

        Product product2 = new Product();
        product2.setDescription("Trousers");
        productRepository.save(product2);

        List<Category> categories = new ArrayList<>();

        Category cat1 = new Category("Clothes");
        Category cat2 = new Category("Male");

        categories.add(cat1);
        categories.add(cat2);

        categoryRepository.save(cat1);
        categoryRepository.save(cat2);
        categoryRepository.save(new Category("Female"));

        product1.setCategories(categories);
        productRepository.save(product1);
    }
}
