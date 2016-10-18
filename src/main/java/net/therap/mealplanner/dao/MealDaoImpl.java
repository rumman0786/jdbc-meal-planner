package net.therap.mealplanner.dao;

import net.therap.mealplanner.connection_manager.MysqlConnector;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rumman
 * @since 10/16/16
 */
public class MealDaoImpl implements MealDao {
    @Override
    public List<Meal> findAll() {
        List<Meal> mealList = new ArrayList<Meal>();
        MenuTypeDaoImpl menuTypeDao = new MenuTypeDaoImpl();
        Connection connection = MysqlConnector.getMysqlConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT id, name, day, menu_type_id FROM meal";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int menu_type_id = rs.getInt("menu_type_id");
                String day = rs.getString("day");
                Set<Dish> dishSet = getDishSetByMealId(id);
                MenuType menuType = menuTypeDao.getMenuType(menu_type_id);
                Meal meal = new Meal(menuType , name, day);
                meal.setId(id);
                meal.setDishSet(dishSet);
                mealList.add(meal);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return mealList;
    }

    @Override
    public List<Meal> findById(int mealId) {
        return null;
    }

    @Override
    public List<Meal> findByName(String mealName) {
        return null;
    }

    @Override
    public boolean insertMeal(Meal meal) {
        List<Meal> mealList = findAll();
        if (!mealList.contains(meal)){
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;

            String insertTableSQL = "INSERT INTO meal (name, day, menu_type_id )VALUES"
                    + "(?,?,?)";

            try {
                dbConnection = MysqlConnector.getMysqlConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);
//                System.out.println(String.valueOf(meal.getMenuType().getId()));
                System.out.println(meal);
                preparedStatement.setString(1, meal.getName());
                preparedStatement.setString(2, meal.getDay());
                preparedStatement.setString(3, String.valueOf(meal.getMenuType().getId()));
                // execute insert SQL stetement
                int insertionId = preparedStatement.executeUpdate();
                insertMealDishMap(insertionId, meal.getDishSet());
                return true;

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateMeal(Meal meal) {

        List<Meal> mealList = findAll();
        if (!mealList.contains(meal)){
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;

            String insertTableSQL = "UPDATE `mealplanner`.`Meal` SET `name` = ?,`calories` = ?, `meal_type` = ? WHERE `id`=?;";

            try {
                dbConnection = MysqlConnector.getMysqlConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);

                preparedStatement.setString(1, meal.getName());
//                preparedStatement.setString(2, meal.getCalories());
//                preparedStatement.setString(3, meal.getMealType());
                preparedStatement.setString(4, String.valueOf(meal.getId()));
                // execute insert SQL stetement
                preparedStatement.executeUpdate();

                return true;

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteMeal(Meal meal) {
        List<Meal> mealList = findAll();
        if (mealList.contains(meal)){
            Statement stmt = null;
            Connection dbConnection = MysqlConnector.getMysqlConnection();
            PreparedStatement preparedStatement = null;

            String deleteSQL = "DELETE FROM `mealplanner`.`Meal` WHERE `id` = ?";

            try {
                preparedStatement = dbConnection.prepareStatement(deleteSQL);
//                preparedStatement.setInt(1, meal.getId());
                // execute delete SQL stetement
                preparedStatement.executeUpdate();

                System.out.println("Record is deleted!");
                return true;
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                } catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean insertMealDishMap(int mealId, Set<Dish> dishSet) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO meal_dish_map (meal_id, dish_id) VALUES "
                + " (?,?)";

        try {
            dbConnection = MysqlConnector.getMysqlConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            for (Dish dish: dishSet){
                preparedStatement.setString(1, String.valueOf(mealId));
                preparedStatement.setString(2, String.valueOf(dish.getId()));
                preparedStatement.executeUpdate();
            }
            return true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        return false;
    }

    public Set<Dish> getDishSetByMealId(int mealId){
        Connection connection = MysqlConnector.getMysqlConnection();
        Statement stmt = null;
        DishDaoImpl dishDao = new DishDaoImpl();
        List<Dish> dishList = dishDao.findAll();
        Set<Dish> dishSet= new HashSet<Dish>();

        try {
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT id, dish_id FROM meal_dish_map WHERE meal_id = " + mealId;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int dish_id = rs.getInt("dish_id");
                for (Dish dish: dishList){
                    if (dish.getId() == dish_id){
                        dishSet.add(dish);
                    }
                }
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dishSet;
    }
}
