package net.therap.mealplanner.dao;

import net.therap.mealplanner.ConnectionManager.MysqlConnector;
import net.therap.mealplanner.entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public class MealDaoImpl implements MealDao {
    @Override
    public List<Meal> findAll() {
        List<Meal> mealList = new ArrayList<Meal>();
        Connection connection = MysqlConnector.getMysqlConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT id, name, meal_type, calories FROM Meal";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mealType = rs.getString("meal_type");
                String calories = rs.getString("calories");
                Meal meal = new Meal(mealType, name, calories);
                meal.setId(id);
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

            String insertTableSQL = "INSERT INTO `mealplanner`.`Meal`(`meal_type`,`name`,`calories`)VALUES"
                    + "(?,?,?)";

            try {
                dbConnection = MysqlConnector.getMysqlConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);

                preparedStatement.setString(1, meal.getMealType());
                preparedStatement.setString(2, meal.getName());
                preparedStatement.setString(3, meal.getCalories());
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
                preparedStatement.setString(2, meal.getCalories());
                preparedStatement.setString(3, meal.getMealType());
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
                preparedStatement.setInt(1, meal.getId());
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
}
