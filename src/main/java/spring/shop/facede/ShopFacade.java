package spring.shop.facede;

import org.apache.commons.collections4.ListUtils;
import spring.shop.entity.Category;
import spring.shop.entity.Product;
import spring.shop.repositories.CategoryRepository;
import spring.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopFacade {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Product saveProduct(Product product) {
        Integer productId = product.getId();
        if(productId != null) {
            Product oldProduct = getProductById(productId);
            product.setCategories(oldProduct.getCategories());
        }
        return productRepository.save(product);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findOne(id);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public List<Category> getProductHasNotCategories(Product product) {

        List<Category> categories = getAllCategories();
        List<Category> productCategories = product.getCategories();

        return ListUtils.subtract(categories, productCategories);
    }

    public void deleteProductById(Integer id) {
        productRepository.delete(id);
    }


    public void addProductToCategory(Product product, Category category) {
        product.addCategory(category);
        productRepository.save(product);
    }

    public void removeProductFromCategory(Product product, Category category) {
        product.removeCategory(category);
        productRepository.save(product);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.delete(id);
    }
}
