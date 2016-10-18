package net.therap.mealplanner.dao;

import net.therap.mealplanner.connection_manager.MysqlConnector;
import net.therap.mealplanner.entity.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumman on 10/18/16.
 */
public class MenuTypeDaoImpl implements MenuTypeDao{
    String [] menuTypes = {"BREAKFAST", "LUNCH"};

    public static void main(String[] args) {
        new MenuTypeDaoImpl().initMenuType();
    }
    @Override
    public void initMenuType() {
        List<Dish> dishList = new ArrayList<Dish>();
        Connection connection = MysqlConnector.getMysqlConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT count(id) AS row_count FROM mealplanner.menu_type WHERE category = \"LUNCH\" OR category = \"BREAKFAST\"; ";
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            if (rs.next()){
                count = Integer.parseInt(rs.getString("row_count"));
            }

            if(menuTypes.length != count && count == 0) {
                insertMenuType(menuTypes);
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
    }

    public boolean insertMenuType(String [] menuTypes) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO menu_type (category) VALUES "
                + "(?)";
        try {
            dbConnection = MysqlConnector.getMysqlConnection();
            for (String type : menuTypes) {
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);
                preparedStatement.setString(1, type);
                // execute insert SQL stetement
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
}
