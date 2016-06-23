package spring.shop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "product_in_category")
    //(name = "product_in_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<AbstractCategory> categories;

    @Size(min = 2, max = 30)
    private String description;

    private Double price;

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AbstractCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<AbstractCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(AbstractCategory category) {
        categories.add(category);

    }

    public void removeCategory(AbstractCategory category) {
        categories.remove(category);
    }
}