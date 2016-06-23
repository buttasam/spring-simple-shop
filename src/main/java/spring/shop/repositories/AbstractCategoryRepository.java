package spring.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import spring.shop.entity.Category;

import java.util.List;

@NoRepositoryBean
public interface AbstractCategoryRepository<T extends Category> extends CrudRepository<T, Integer> {

    List<T> findAll();
}
