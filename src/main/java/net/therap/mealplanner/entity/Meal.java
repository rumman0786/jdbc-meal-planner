package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author rumman
 * @since 10/16/16
 */
@Entity()
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false, length = 11)
    private String name;

    // Type of menu this meal is i.e. breakfast of lunch

    @ManyToOne
    @JoinColumn(name = "menu_type_id",nullable = false)
    private MenuType menuType;

    // Dishes that belong to this meal
    @ManyToMany(targetEntity = Dish.class, fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name="meal_dish_map",
            joinColumns={@JoinColumn(name="meal_id")},
            inverseJoinColumns={@JoinColumn(name="dish_id")})
    private Set<Dish> dishSet;

    @Column(name = "day",nullable = false, length = 11)
    private String day;

    public Meal() {
    }

    public Meal(MenuType menuType, String name, String day) {
        this.menuType = menuType;
        this.name = name;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof Meal) {
            String mealName = ((Meal) object).getName();
            isEqual = (this.name.equals(mealName));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getName().toLowerCase().hashCode() * 17;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", menuType='" + menuType + '\'' +
                ", name='" + name + '\'' +
                ", dishes='" + dishSet + '\'' +
                ", day='" + day + '\'';
    }
}
