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
    private String day;

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
