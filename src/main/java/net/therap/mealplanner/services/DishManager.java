package net.therap.mealplanner.services;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;

import java.util.List;

/**
 * @author rumman
 * @since 10/17/16
 */
public class DishManager {

    public boolean addDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.insertDish(dish);
    }

    public boolean updateDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.updateDish(dish);
    }

    public boolean deleteDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.deleteDish(dish);
    }

    //TODO move to handlers
    public void printDishes() {
        DishDao dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        System.out.println("Current Dishes are:\n");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
    }
}
