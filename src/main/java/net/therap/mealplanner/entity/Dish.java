package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.*;

/**
 * @author rumman
 * @since 10/17/16
 */
@Entity
@Table(name="dish")
public class Dish {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private String calories;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="meal_dish_map",
            joinColumns={@JoinColumn(name="meal_id")},
            inverseJoinColumns={@JoinColumn(name="dish_id")})
    private Set<Meal> meals;

    public Dish() {
    }

    public Dish(String name, String calories) {
        this.name = name;
        this.calories = calories;
    }

    public Dish(String name, String calories, Set<Meal> meals) {
        this.name = name;
        this.calories = calories;
        this.meals = meals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof Dish) {
            int dishId = ((Dish) object).getId();
            isEqual = this.id == dishId;
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getId() * 17;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' + '}';
//                ", meals=" + meals +
//                '}';
    }
}
