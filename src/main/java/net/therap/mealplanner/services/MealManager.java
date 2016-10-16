package net.therap.mealplanner.services;

import net.therap.mealplanner.DAO.MealDao;
import net.therap.mealplanner.DAO.MealDaoImpl;
import net.therap.mealplanner.entity.Meal;

import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public class MealManager {

    public boolean addMealToMenu(Meal meal){
        MealDao mealDao = new MealDaoImpl();
        return mealDao.insertMeal(meal);
    }

    public boolean updateMealInMenu(Meal meal){
        MealDao mealDao = new MealDaoImpl();
        return mealDao.updateMeal(meal);
    }

    public boolean deleteMealFromMenu(Meal meal){
        MealDao mealDao = new MealDaoImpl();
        return mealDao.deleteMeal(meal);
    }

    public void printMeals(){
        MealDao mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.findAll();
        System.out.println("Current Meals are:\n");
        for (Meal meal : mealList){
            System.out.println(meal);
        }
    }


}
