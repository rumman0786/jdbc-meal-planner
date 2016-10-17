package net.therap.mealplanner.entity;

import java.util.Set;

/**
 * @author rumman
 * @since 10/17/16
 */
public class Day {
    private int id;
    private String name;//Friday to Thursday
    // Meals that are available in a day.
    private Set<Meal> mealSet;

    public Day(String name) {
        this.name = name;
    }

    public Day(String name, Set<Meal> mealSet) {
        this.name = name;
        this.mealSet = mealSet;
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

    public Set<Meal> getMealSet() {
        return mealSet;
    }

    public void setMealSet(Set<Meal> mealSet) {
        this.mealSet = mealSet;
    }
}
