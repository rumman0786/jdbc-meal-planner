package net.therap.mealplanner.app;

import net.therap.mealplanner.DAO.MealDaoImpl;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.services.MealManager;
import net.therap.mealplanner.utils.Handlers;

import java.util.List;
import java.util.Scanner;

/**
 * @author rumman
 * @since 10/16/16
 */
public class Main {

    public static void main(String[] args) {
        Handlers handlers = new Handlers();
        handlers.startApp();
    }
}