package Views;

import Controllers.UserControl;
import util.Input;

public class UserView extends Input {
    
    
    public void tosignUpLogin() {
        boolean f = true;
        while (f) {
            try {
                System.out.println("***************************************************");

                System.out.println("Orphanage Management System");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.println("***************************************************");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline character
                
                switch (choice) {
                    case 1:
                        getLogin();
                        break;
                    case 2:
                        toSignUp();
                        break;
                    case 3:
                        System.out.println("Thank you for visiting.");
                        f = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception err) {
                System.out.println("An error occurred: " + err.getMessage());
                // Clear scanner buffer to prevent infinite loop
                sc.nextLine();
            }
        }
    }

    public void getLogin() {
        try {
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            UserControl userController = new UserControl();
            int result = userController.login(email, password);
            if (result == 1) {
                System.out.println("Login successful.");
                // Proceed to user dashboard or other actions
                // Example: displayDashboard();
                // System.out.println("Welcome, Landlord!");
                // Redirect to StaffView
                // StaffView staffView = new StaffView();
                // staffView.displayMenu();
                // DonationView donationView = new DonationView();
                // donationView.displayMenu();
                displayUserMenu();
            }
             else {
                System.out.println("Login failed. Please check your credentials.");
            }
        } catch (Exception err) {
            System.out.println("An error occurred: " + err.getMessage());
        }
    }
        private void displayUserMenu() {
            // Display menu options for the user
            System.out.println("Choose an option:");
            System.out.println("1. View Staff Details");
            System.out.println("2. View Donation Details");
            System.out.println("3. Log out");
        
            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
        
            // Handle user choice
            switch (choice) {
                case 1:
                    StaffView staffView = new StaffView();
                    staffView.displayMenu();
                    break;
                case 2:
                    DonationView donationView = new DonationView();
                    // donationView.displayMenu();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    

    public void toSignUp() {
        try {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            // Additional fields for signing up can be added here
            System.out.print("Enter phone number: ");
            String phone = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();

            UserControl userController = new UserControl();
            int result = userController.signUp(name, email, password, phone, "staff", address);
            if (result == 1) {
                System.out.println("Sign up successful.");
                // Proceed to login after successful sign up
                tosignUpLogin();
            } else {
                System.out.println("Sign up failed.");
            }
        } catch (Exception err) {
            System.out.println("An error occurred: " + err.getMessage());
        }
    }
        
}

