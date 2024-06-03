package Views;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Controllers.StaffController;
import Resources.DonationDTO;
import Resources.HealthMonitorDTO;
import Resources.OrphanDTO;
import Resources.StaffDTO;
import util.Input;

public class StaffView extends Input {

    private StaffController staffController;
    private Connection connection;

    public StaffView() {
        this.connection=connection;
        this.staffController = new StaffController(connection); // Initialize the StaffController
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Orphanage Management System");
            System.out.println("1. View All Orphans");
            System.out.println("2. View Orphan Details");
            System.out.println("3. View All Staff Members");
            System.out.println("4. View Donations");
            System.out.println("5. View Health Records");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    viewAllOrphans();
                    break;
                case 2:
                    viewOrphanDetails();
                    break;
                case 3:
                    viewAllStaffMembers();
                    break;
                case 4:
                    viewDonations();
                    break;
                case 5:
                    viewHealthRecords();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllOrphans() {
        try {
            List<OrphanDTO> orphans = staffController.getAllOrphans();
            for (OrphanDTO orphan : orphans) {
                System.out.println(orphan); // Implement toString() method in OrphanDTO
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewOrphanDetails() {
        System.out.print("Enter orphan ID: ");
        int orphanId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        try {
            OrphanDTO orphan = staffController.getOrphanDetails(orphanId);
            if (orphan != null) {
                System.out.println(orphan); // Implement toString() method in OrphanDTO
            } else {
                System.out.println("Orphan not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllStaffMembers() {
        try {
            List<StaffDTO> staffMembers = staffController.getAllStaff();
            for (StaffDTO staff : staffMembers) {
                System.out.println(staff); // Implement toString() method in StaffDTO
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewDonations() {
        try {
            List<DonationDTO> donations = staffController.getAllDonations();
            for (DonationDTO donation : donations) {
                System.out.println(donation); // Implement toString() method in DonationDTO
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewHealthRecords() {
        System.out.print("Enter orphan ID: ");
        int orphanId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        try {
            List<HealthMonitorDTO> healthRecords = staffController.getHealthRecordsForOrphan(orphanId);
            for (HealthMonitorDTO record : healthRecords) {
                System.out.println(record); // Implement toString() method in HealthMonitorDTO
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
