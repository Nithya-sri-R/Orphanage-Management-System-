package Views;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
// import java.util.Scanner;
import java.sql.Date;
import Controllers.DonationControl;
import Resources.DonationDTO;
import util.Input;
// import java.util.Date;
public class DonationView extends Input {

    public void displayMenu() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("***************************************************");
            System.out.println("*              Donation Management System         *");
            System.out.println("* 1. View All Donations                           *");
            System.out.println("* 2. Make a Donation                             *");
            System.out.println("* 3. Exit                                         *");
            System.out.println("***************************************************");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllDonations();
                    break;
                case 2:
                    makeDonation();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void viewAllDonations() {
        try {
            DonationControl donationControl = new DonationControl();
            List<DonationDTO> donations = donationControl.getAllDonations();
            if (!donations.isEmpty()) {
                System.out.println("List of all donations:");
                for (DonationDTO donation : donations) {
                    System.out.println("------------------------------------");
                    System.out.println("Donation ID: " + donation.getDonationId());
                    System.out.println("Amount: " + donation.getAmount());
                    System.out.println("Date: " + donation.getDate());
                    System.out.println("Donor Name: " + donation.getDonorName());
                }
            } else {
                System.out.println("No donations found.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching donations: " + e.getMessage());
        }
    }

    private void makeDonation() {
        try {
            System.out.print("Enter donation amount: ");
            double amount = sc.nextDouble();
            sc.nextLine(); // Consume newline
            System.out.print("Enter donor name: ");
            String donorName = sc.nextLine();
            java.util.Date currentDate = Calendar.getInstance().getTime();
            Date date = new Date(currentDate.getTime()); // Current date

            DonationControl donationControl = new DonationControl();
            donationControl.makeDonation(amount, date, donorName);
            System.out.println("Donation made successfully.");
        } catch (SQLException e) {
            System.out.println("Error occurred while making donation: " + e.getMessage());
        }
    }
}

