package spring.shop.facede;

import org.apache.commons.collections4.ListUtils;
import spring.shop.entity.AbstractCategory;
import spring.shop.entity.Category;
import spring.shop.entity.Product;
import spring.shop.entity.SpecialCategory;
import spring.shop.repositories.CategoryRepository;
import spring.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.shop.repositories.SpecialCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopFacade {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SpecialCategoryRepository specialCategoryRepository;


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

    public SpecialCategory saveSpecialCategory(SpecialCategory specialCategory) {
        return specialCategoryRepository.save(specialCategory);
    }

    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    public AbstractCategory getGeneralCategoryById(Integer id) {
        AbstractCategory category = categoryRepository.findOne(id);
        if(category == null) {
            category = specialCategoryRepository.findOne(id);
        }
        return category;
    }


    public SpecialCategory getSpecialCategoryById(Integer id) {
        return specialCategoryRepository.findOne(id);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<SpecialCategory> getAllSpecialCategories() {
        return specialCategoryRepository.findAll();
    }


    public List<AbstractCategory> getProductHasNotCategories(Product product) {

        List<AbstractCategory> categories = new ArrayList<>();

        getAllCategories().forEach(c -> categories.add(c));
        getAllSpecialCategories().forEach(c -> categories.add(c));

        List<AbstractCategory> productCategories = product.getCategories();

        return ListUtils.subtract(categories, productCategories);
    }

    public void deleteProductById(Integer id) {
        productRepository.delete(id);
    }


    public void addProductToCategory(Product product, AbstractCategory category) {
        product.addCategory(category);
        productRepository.save(product);
    }

    public void removeProductFromCategory(Product product, AbstractCategory category) {
        product.removeCategory(category);
        productRepository.save(product);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.delete(id);
    }

    public void deleteSpecialCategoryById(Integer id) {
        specialCategoryRepository.delete(id);
    }
}
