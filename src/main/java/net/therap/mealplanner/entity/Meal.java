package net.therap.mealplanner.entity;

import java.util.Set;

/**
 * @author rumman
 * @since 10/16/16
 */
public class Meal {

    private int id;
    private String name;
    // Type of menu this meal is i.e. breakfast of lunch
    private MenuType menuType;
    // Dishes that belong to this meal
    private Set<Dish> dishSet;
    // Days when this meal is available
    private Set<Day> daySet;

    public Meal(MenuType menuType, String name) {
        this.menuType = menuType;
        this.name = name;
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

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof Meal) {
            String mealName = ((Meal) object).getName();
            isEqual = (this.name.equals(mealName));
        }

        return isEqual;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }

    public Set<Day> getDaySet() {
        return daySet;
    }

    public void setDaySet(Set<Day> daySet) {
        this.daySet = daySet;
    }

    @Override
    public int hashCode() {
        return this.getName().toLowerCase().hashCode() * 17;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", mealType='" + menuType + '\'' +
                ", name='" + name + '\'';
    }
}
