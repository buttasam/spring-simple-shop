package spring.shop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Inheritance
public abstract class AbstractCategory {


    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, max = 30)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public AbstractCategory() {
    }

    public AbstractCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
