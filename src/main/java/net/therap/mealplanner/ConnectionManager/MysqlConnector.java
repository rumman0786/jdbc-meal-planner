package net.therap.mealplanner.ConnectionManager;

import java.sql.*;

/**
 * @author rumman
 * @since 10/16/16
 */
public class MysqlConnector {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mealplanner";

    //  Database credentials
    static final String DB_USER = "root";
    static final String DB_PASSWORD= "root";

    public static Connection getMysqlConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
