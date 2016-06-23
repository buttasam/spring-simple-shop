package spring.shop.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import spring.shop.entity.AbstractCategory;
import spring.shop.entity.Category;
import spring.shop.entity.Product;
import spring.shop.entity.SpecialCategory;
import spring.shop.repositories.CategoryRepository;
import spring.shop.repositories.ProductRepository;
import spring.shop.repositories.SpecialCategoryRepository;

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

    @Autowired
    private SpecialCategoryRepository specialCategoryRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product product1 = new Product();
        product1.setDescription("Shirt");
        product1.setPrice(250.0);

        Product product2 = new Product();
        product2.setDescription("Trousers");
        productRepository.save(product2);

        List<AbstractCategory> categories = new ArrayList<>();

        Category cat1 = new Category("Clothes");
        SpecialCategory cat2 = new SpecialCategory("Male", "Nice super title");

        categories.add(cat1);
        categories.add(cat2);

        categoryRepository.save(cat1);
        specialCategoryRepository.save(cat2);
        categoryRepository.save(new Category("Female"));

        product1.setCategories(categories);
        productRepository.save(product1);
    }
}
