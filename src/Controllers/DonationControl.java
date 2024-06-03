package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
// import java.sql.Date;

import Models.DonationDAO;
import Resources.DonationDTO;

public class DonationControl {
    
    private DonationDAO donationDAO;
    private Connection connection;
    
    public DonationControl() {
        this.donationDAO = new DonationDAO(connection);
    }
    
    public List<DonationDTO> getAllDonations() throws SQLException {
        return donationDAO.getAllDonations();
    }
    
    public void makeDonation(double amount, java.sql.Date date, String donorName) throws SQLException {
        // Create a DonationDTO object with the provided parameters
        DonationDTO donation = new DonationDTO();
        donation.setAmount(amount);
        donation.setDate(date);
        donation.setDonorName(donorName);
        
        // Pass the DonationDTO object to the addDonation method
        donationDAO.addDonation(donation);
    }
    
    public int getOrphanCount() throws SQLException {
        // Logic to get the count of orphans
        // This might involve querying the database or any other data source
        return 100; // Just a placeholder value, replace it with actual logic
    }
    
    public double getTotalDonations() throws SQLException {
        // Logic to calculate total donations
        // This might involve querying the database or any other data source
        return 5000.0; // Just a placeholder value, replace it with actual logic
    }
    
    
    
    
    // Other methods related to donation management can be added here
    
}

