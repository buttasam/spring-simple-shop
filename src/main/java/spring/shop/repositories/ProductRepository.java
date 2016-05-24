package spring.shop.repositories;

import spring.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Integer> {


    List<Product> findAll();

    Product findFirstByOrderByDescriptionDesc();

}
