package net.therap.mealplanner.dao;

import net.therap.mealplanner.connection_manager.MysqlConnector;
import net.therap.mealplanner.entity.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public class DishDaoImpl implements DishDao {

    @Override
    public List<Dish> findAll() {
        List<Dish> dishList = new ArrayList<Dish>();
        Connection connection = MysqlConnector.getMysqlConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT id, name, calories FROM dish";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String calories = rs.getString("calories");
                Dish dish = new Dish(name, calories);
                dish.setId(id);
                dishList.add(dish);
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
        return dishList;
    }

    @Override
    public List<Dish> findById(int dishId) {
        return null;
    }

    @Override
    public List<Dish> findByName(String dishName) {
        return null;
    }

    @Override
    public boolean insertDish(Dish dish) {
        List<Dish> dishList = findAll();
        if (!dishList.contains(dish)) {
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;

            String insertTableSQL = "INSERT INTO dish (name, calories) VALUES "
                    + "(?,?)";

            try {
                dbConnection = MysqlConnector.getMysqlConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);
                preparedStatement.setString(1, dish.getName());
                preparedStatement.setString(2, dish.getCalories());
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
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteDish(Dish dish) {
        List<Dish> dishList = findAll();
        if (dishList.contains(dish)) {
            Statement stmt = null;
            Connection dbConnection = MysqlConnector.getMysqlConnection();
            PreparedStatement preparedStatement = null;

            String deleteSQL = "DELETE FROM dish WHERE id = ?";

            try {
                preparedStatement = dbConnection.prepareStatement(deleteSQL);
                preparedStatement.setInt(1, dish.getId());
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
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateDish(Dish dish) {

        List<Dish> dishList = findAll();
        if (dishList.contains(dish)) {
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;
            String insertTableSQL = "UPDATE dish SET name = ?,calories = ? WHERE id = ? ;";

            try {
                dbConnection = MysqlConnector.getMysqlConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);

                preparedStatement.setString(1, dish.getName());
                preparedStatement.setString(2, dish.getCalories());
                preparedStatement.setString(3, String.valueOf(dish.getId()));
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
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return false;
    }
}
