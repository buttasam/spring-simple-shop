package spring.shop.entity;

import javax.persistence.Entity;

@Entity
public class SpecialCategory extends AbstractCategory {

    private String specialText;

    public SpecialCategory() {
    }

    public SpecialCategory(String name, String specialText) {
        super(name);
        this.specialText = specialText;
    }

    public String getSpecialText() {
        return specialText;
    }

    public void setSpecialText(String specialText) {
        this.specialText = specialText;
    }
}
