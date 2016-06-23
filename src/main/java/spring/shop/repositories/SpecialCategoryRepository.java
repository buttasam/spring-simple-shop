package spring.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.shop.entity.Category;
import spring.shop.entity.SpecialCategory;

import java.util.List;

public interface SpecialCategoryRepository extends CrudRepository<SpecialCategory, Integer> {

    List<SpecialCategory> findAll();
}
