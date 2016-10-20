package net.therap.mealplanner.utils;

import net.therap.mealplanner.dao.*;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.services.DishManager;
import net.therap.mealplanner.services.MealManager;

import java.util.*;

/**
 * @author rumman
 * @since 10/16/16
 */
public class Handlers {
    public static final String LUNCH = "LUNCH";
    public static final String BREAKFAST = "BREAKFAST";

    public void startApp() {
        // Initialize different Menu Types.
        MenuTypeDao menuTypeDao = new MenuTypeDaoImpl();
        menuTypeDao.initMenuType();

        MealManager mealManager = new MealManager();
        DishManager dishManager = new DishManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPress 1 to show current Meals");
            System.out.println("Press 2 to add a Meal");
            System.out.println("Press 3 to update a Meal");
            System.out.println("Press 4 to delete a Meal");
            System.out.println("Press 5 to show current Dishes");
            System.out.println("Press 6 to add a Dish");
            System.out.println("Press 7 to update a Dish");
            System.out.println("Press 8 to delete a Dish");
            System.out.println("Press 9 to exit");
            System.out.println("Please choose an option:");
            System.out.println("============================");
            int userInput = 0;
            try {
                userInput = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ne) {
                System.out.println("Invalid Input Try Again");
                continue;
            }

            Meal meal = null;
            Dish dish = null;
            boolean status = false;
            switch (userInput) {
                case 1:
                    printMeals();
                    break;
                case 2:
                    meal = getMealFromUser();
                    status = mealManager.addMealToMenu(meal);
                    if (status) {
                        System.out.println("Meal Added");
                    } else {
                        System.out.println("Meal with that name already exists, Try Again");
                    }
                    break;
                case 3:
                    meal = getUpdatedMeal();
                    status = mealManager.updateMealInMenu(meal);
                    if (status) {
                        System.out.println("Meal Updated");
                    } else {
                        System.out.println("Meal not Updated, Try Again");
                    }
                    break;
                case 4:
                    meal = getMealToBeDeleted();
                    status = mealManager.deleteMealFromMenu(meal);
                    if (status) {
                        System.out.println("Meal Deleted");
                    } else {
                        System.out.println("Meal not Deleted, Try Again");
                    }
                    break;
                case 5:
                    printDishes();
                    break;
                case 6:
                    dish = getDishFromUser();
                    status = dishManager.addDish(dish);
                    if (status) {
                        System.out.println("Dish Added");
                    } else {
                        System.out.println("Dish with that name already exists, Try Again");
                    }
                    break;
                case 7:
                    dish = getUpdatedDish();
                    status = dishManager.updateDish(dish);
                    if (status) {
                        System.out.println("Dish Updated");
                    } else {
                        System.out.println("Dish not Updated, Try Again");
                    }
                    break;
                case 8:
                    dish = getDishToBeDeleted();
                    status = dishManager.deleteDish(dish);
                    if (status) {
                        System.out.println("Dish Deleted");
                    } else {
                        System.out.println("Dish not Deleted, Try Again");
                    }
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Unknown option please try again");
            }
        }
    }

    public Meal getMealFromUser() {
        Scanner scanner = new Scanner(System.in);
        DishManager dishManager = new DishManager();
        Set<Dish> dishSet = getDishSet();
        System.out.println("Please Enter a meal name:\n");
        String name = scanner.nextLine();
//        improve later
//        System.out.println("Please Enter a day name:\n");
        String day = getDayFromUser();

        MenuTypeDaoImpl menuTypeDao = new MenuTypeDaoImpl();
        System.out.println("Press 1 if meal is breakfast 2 if lunch:\n");
        String typeNum = scanner.nextLine();
        String type = (typeNum.equals("1")) ? BREAKFAST : LUNCH;
        MenuType menuType = menuTypeDao.getMenuType(type);
        Meal meal = new Meal(menuType, name, day);
        meal.setDishSet(dishSet);
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
        }
        if (!isMealAvailable) {
            System.out.println("No Meal exists with that id.");
        } else {
            System.out.println("Please enter updated meal name:\n");
            String name = scanner.nextLine();
//            System.out.println("Please Enter updated day name:\n");
            String day = getDayFromUser();
            MenuTypeDaoImpl menuTypeDao = new MenuTypeDaoImpl();
            System.out.println("Press 1 if meal is breakfast 2 if lunch:\n");
            String typeNum = scanner.nextLine();
            String type = (typeNum.equals("1")) ? BREAKFAST : LUNCH;
            MenuType menuType = menuTypeDao.getMenuType(type);
            Set<Dish> dishSet = getDishSet();

            mealTobeUpdated.setName(name);
            mealTobeUpdated.setDay(day);
            mealTobeUpdated.setMenuType(menuType);
            mealTobeUpdated.setDishSet(dishSet);
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

    public Dish getDishFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter a dish name:\n");
        String name = scanner.nextLine();
        System.out.println("Please calories that dish contains:\n");
        String calories = scanner.nextLine();
        Dish dish = new Dish(name, calories);
        return dish;
    }

    public Dish getUpdatedDish() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the id of the dish you want to update:\n");
        DishDaoImpl dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        for (Dish loopDish : dishList) {
            System.out.println(loopDish);
        }
        int dishId = Integer.parseInt(scanner.nextLine());
        Dish dishTobeUpdated = null;
        boolean isMealAvailable = false;
        for (Dish loopDish : dishList) {
            if (loopDish.getId() == dishId) {
                isMealAvailable = true;
                dishTobeUpdated = loopDish;
                break;
            }
        }
        if (!isMealAvailable) {
            System.out.println("No Dish exists with that id.");
        } else {
            System.out.println("Please enter updated dish name:\n");
            String name = scanner.nextLine();
            System.out.println("Please enter updated calories that dish contains:\n");
            String calories = scanner.nextLine();
            dishTobeUpdated.setName(name);
            dishTobeUpdated.setCalories(calories);
        }
        return dishTobeUpdated;
    }

    public Dish getDishToBeDeleted() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter the id of the dish you want to delete:\n");
        DishDaoImpl dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        for (Dish loopDish : dishList) {
            System.out.println(loopDish);
        }
        int dishId = Integer.parseInt(scanner.nextLine());
        for (Dish loopDish : dishList) {
            if (loopDish.getId() == dishId) {
                return loopDish;
            }
        }
        System.out.println("No Dish exists with that id.");
        return null;
    }

    public Set<Dish> getDishSet() {
        DishDaoImpl dishDao = new DishDaoImpl();
        DishManager dishManager = new DishManager();

        System.out.println("Enter id of the dish you want to add to your meal, Enter X to stop");
        printDishes();
        Set<Dish> dishSet = new HashSet<Dish>();
        List<Dish> dishList = dishDao.findAll();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String rawInput = null;
            int userInput = 0;
            try {
                rawInput = scanner.nextLine();
                userInput = Integer.parseInt(rawInput);
            } catch (NumberFormatException ne) {
                if (rawInput.equals("X")) {
                    break;
                }
                System.out.println("Invalid Input Try Again");
                continue;
            }
            for (Dish dish : dishList) {
                if (dish.getId() == userInput) {
                    dishSet.add(dish);
                }
            }
        }
        return dishSet;
    }

    public void printDishes() {
        DishDao dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        System.out.println("Current Dishes are:\n");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
    }

    public void printMeals() {
        MealDao mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.findAll();
        System.out.println("Current Meals are:\n");
        for (Meal meal : mealList) {
            System.out.println(meal);
        }
    }

    public String getDayFromUser() {
        Scanner scanner = new Scanner(System.in);
        String [] validDays = {"SATURDAY", "SUNDAY", "MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
        List<String> dayList = Arrays.asList(validDays);
        System.out.println("Please Enter a day name:\n");
        String day = scanner.nextLine().toUpperCase();

        if (!dayList.contains(day)){
            System.out.println("That is not a valid day");
            return getDayFromUser();
        }
        return day;
    }
}
