package net.therap.mealplanner.utils;

import net.therap.mealplanner.DAO.MealDaoImpl;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.services.MealManager;

import java.util.List;
import java.util.Scanner;

/**
 * @author rumman
 * @since 10/16/16
 */
public class Handlers {
    public static final String LUNCH = "lunch";
    public static final String BREAKFAST = "breakfast";

    public void startApp() {
        MealManager manager = new MealManager();
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
                    meal = getMealFromUser();
                    status = manager.addMealToMenu(meal);
                    if (status) {
                        System.out.println("Meal Added");
                    } else {
                        System.out.println("Meal with that name already exists, Try Again");
                    }
                    break;
                case 3:
                    meal = getUpdatedMeal();
                    status = manager.updateMealInMenu(meal);
                    if (status) {
                        System.out.println("Meal Updated");
                    } else {
                        System.out.println("Meal not Updated, Try Again");
                    }
                    break;
                case 4:
                    meal = getMealToBeDeleted();
                    status = manager.deleteMealFromMenu(meal);
                    if (status) {
                        System.out.println("Meal Deleted");
                    } else {
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

    public Meal getMealFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter a meal name:\n");
        String name = scanner.nextLine();
        System.out.println("Please calories that meal contains:\n");
        String calories = scanner.nextLine();
        System.out.println("Press 1 if meal is breakfast 2 if lunch:\n");
        String typeNum = scanner.nextLine();
        String type = (typeNum.equals("1")) ? BREAKFAST : LUNCH;
        Meal meal = new Meal(type, name, calories);
        return meal;
    }

    public Meal getUpdatedMeal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the id of the meal you want to update:\n");
        MealDaoImpl mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.findAll();
        for (Meal loopMeal : mealList) {
            System.out.println(loopMeal);
        }
        int mealId = Integer.parseInt(scanner.nextLine());
        Meal mealTobeUpdated = null;
        boolean isMealAvailable = false;
        for (Meal loopMeal : mealList) {
            if (loopMeal.getId() == mealId) {
                isMealAvailable = true;
                mealTobeUpdated = loopMeal;
                break;
            }
            System.out.println(loopMeal);
        }
        if (!isMealAvailable) {
            System.out.println("No Meal exists with that id.");
        } else {
            System.out.println("Please enter updated meal name:\n");
            String name = scanner.nextLine();
            System.out.println("Please enter updated calories that meal contains:\n");
            String calories = scanner.nextLine();
            System.out.println("Press 1 if meal is breakfast 2 if lunch:\n");
            String typeNum = scanner.nextLine();
            String type = (typeNum.equals("1")) ? BREAKFAST : LUNCH;
            mealTobeUpdated.setName(name);
            mealTobeUpdated.setCalories(calories);
            mealTobeUpdated.setMealType(type);
        }
        return mealTobeUpdated;
    }

    public Meal getMealToBeDeleted() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the id of the meal you want to delete:\n");
        MealDaoImpl mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.findAll();
        for (Meal loopMeal : mealList) {
            System.out.println(loopMeal);
        }
        int mealId = Integer.parseInt(scanner.nextLine());
        for (Meal loopMeal : mealList) {
            if (loopMeal.getId() == mealId) {
                return loopMeal;
            }
        }
        System.out.println("No Meal exists with that id.");
        return null;
    }
}
