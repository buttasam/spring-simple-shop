package spring.shop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Category extends AbstractCategory {

    public Category() {
    }

    public Category(String name) {
        super(name);
    }
}
