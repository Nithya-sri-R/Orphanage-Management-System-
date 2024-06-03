import java.sql.SQLException;

import Models.Connect;
import Views.UserView;
import util.Input;

public class App extends Input {
    public static void main(String[] args) {
        try {
            // Establish database connection
            Connect.getConnection();
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Database connection error:");
            System.out.println(e.getMessage());
        }

        // Initialize UserView and display the main menu
        UserView userView = new UserView();
        userView.tosignUpLogin();
// Assuming displayMenu() is the method to show the main menu
    }
}
