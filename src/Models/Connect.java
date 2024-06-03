package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import util.Env;

public class Connect {
    protected static Connection con;
    protected static Statement st;

    public static void getConnection() throws SQLException {
        try {
            // Establish the database connection using environment variables
            con = DriverManager.getConnection(Env.databaseURL, Env.databaseUser, Env.databasePassword);
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw e;
        }
    }
}


