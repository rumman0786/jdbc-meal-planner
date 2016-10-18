package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;

import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public interface DishDao {
    List<Dish> findAll();

    List<Dish> findById(int dishId);

    List<Dish> findByName(String dishName);

    boolean insertDish(Dish dish);

    boolean updateDish(Dish dish);

    boolean deleteDish(Dish dish);

}