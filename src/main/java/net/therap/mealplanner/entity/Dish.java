package net.therap.mealplanner.entity;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author rumman
 * @since 10/17/16
 */
public class Dish {
    private int id;
    private String name;
    private String calories;
    private Set<Meal> meals;

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
