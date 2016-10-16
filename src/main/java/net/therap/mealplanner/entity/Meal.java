package net.therap.mealplanner.entity;

/**
 * @author rumman
 * @since 10/16/16
 */
public class Meal {

    private int id;
    private String mealType;
    private String name;
    private String calories;

    public Meal(String mealType, String name, String calories) {
        this.mealType = mealType;
        this.name = name;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
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
                ", mealType='" + mealType + '\'' +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'';
    }
}
