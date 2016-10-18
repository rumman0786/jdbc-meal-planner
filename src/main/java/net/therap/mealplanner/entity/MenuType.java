package net.therap.mealplanner.entity;

/**
 * @author rumman
 * @since 10/17/16
 */
public class MenuType {
    private int id;
    private String category; //can be breakfast,lunch, dinner or anything can be added later on

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
