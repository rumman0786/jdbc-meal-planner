package net.therap.mealplanner.entity;

import javax.persistence.*;

/**
 * @author rumman
 * @since 10/17/16
 */
@Entity
@Table(name = "menu_type", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class MenuType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "category", nullable = false, length = 11)
    private String category; //can be breakfast,lunch, dinner or anything can be added later on

    public MenuType() {
    }

    public MenuType(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof MenuType) {
            int menuTypeId = ((MenuType) object).getId();
            isEqual = this.id == menuTypeId;
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getId() * 17;
    }

    @Override
    public String toString() {
        return "MenuType{" +
                "category='" + category + '\'' +
                '}';
    }
}
