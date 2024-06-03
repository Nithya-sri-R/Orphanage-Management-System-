package Resources;

import java.sql.Date;

public class DonationDTO {
    private int donationId;
    private double amount;
    private String donorName;
    private Date date;

    // Default constructor
    public DonationDTO() {
        // You can initialize default values here if needed
    }

    // Constructor with parameters
    public DonationDTO(int donationId, double amount, String donorName, Date date) {
        this.donationId = donationId;
        this.amount = amount;
        this.donorName = donorName;
        this.date = date;
    }

    // Getters and setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
