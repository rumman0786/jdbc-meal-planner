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
//        MealDaoImpl mealDao = new MealDaoImpl();
//        List<Meal> mealList = mealDao.findAll();
//        Meal meal = new Meal("breakfast","Rice","100");
//        meal = new Meal("breakfast","xisce","100");
//        mealDao.insertMeal(meal);
//        int deleteId = -1;
//        Meal meala = null;
//        for (Meal loopMeal : mealList){
//            System.out.println(loopMeal.getId() + "::"+loopMeal.getName());
//            deleteId = loopMeal.getId();
//            meala = loopMeal;
//        }
//        System.out.println("============================");
//        System.out.println("Id to be deleted" + deleteId);
//        System.out.println("============================");
//        System.out.println(meala);
//        mealDao.deleteMeal(meala);
//        System.out.println("============================");
//        meala.setName("food");
//        mealDao.updateMeal(meala);
        MealManager manager = new MealManager();
        Handlers handlers = new Handlers();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPress 1 to show current Meals");
            System.out.println("Press 2 to add a Meal");
            System.out.println("Press 3 to update a Meal");
            System.out.println("Press 4 to delete a Meal");
            System.out.println("Press 5 to exit");
            System.out.println("Please choose and option:");
            System.out.println("============================");
            int userInput = Integer.parseInt(scanner.nextLine());
            Meal meal = null;
            boolean status = false;
            switch (userInput) {
                case 1:
                    manager.printMeals();
                    break;
                case 2:
                    meal = handlers.getMealFromUser();
                    status = manager.addMealToMenu(meal);
                    if (status){
                        System.out.println("Meal Added");
                    }else {
                        System.out.println("Meal with that name already exists, Try Again");
                    }
                    break;
                case 3:
                    meal = handlers.getUpdatedMeal();
                    status = manager.updateMealInMenu(meal);
                    if (status){
                        System.out.println("Meal Updated");
                    }else {
                        System.out.println("Meal not Updated, Try Again");
                    }
                    break;
                case 4:
                    meal = handlers.getMealToBeDeleted();
                    status = manager.deleteMealFromMenu(meal);
                    if (status){
                        System.out.println("Meal Deleted");
                    }else {
                        System.out.println("Meal not Deleted, Try Again");
                    }
                    break;
                case 5:
                    System.exit(1);
                default:
                    System.out.println("Unknown option please try again");
            }
        }

    }
}