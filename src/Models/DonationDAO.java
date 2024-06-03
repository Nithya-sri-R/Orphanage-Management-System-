package Models;

import java.sql.Connection;
// import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.DonationDTO;

public class DonationDAO extends Connect {
    private static DonationDAO instance;
    private PreparedStatement addDonationStatement;
    private PreparedStatement viewDonationsStatement;
    private Connection connection;

    public DonationDAO(Connection connection) {
        this.connection = connection;
    }



    private DonationDAO() throws SQLException {
        addDonationStatement = con.prepareStatement("INSERT INTO donations (amount, donor_name, date) VALUES (?, ?, ?)");
        viewDonationsStatement = con.prepareStatement("SELECT * FROM donations");
    }

    public static DonationDAO getInstance(Connection connection) throws SQLException {
        if (instance == null) {
            instance = new DonationDAO(connection);
        }
        return instance;
    }

    public void addDonation(DonationDTO donation) throws SQLException {
        addDonationStatement.setDouble(1, donation.getAmount());
        addDonationStatement.setString(2, donation.getDonorName());
        addDonationStatement.setDate(3, donation.getDate());
        addDonationStatement.executeUpdate();
    }

    public List<DonationDTO> getAllDonations() throws SQLException {
        List<DonationDTO> donations = new ArrayList<>();
        ResultSet resultSet = viewDonationsStatement.executeQuery();
        while (resultSet.next()) {
            DonationDTO donation = new DonationDTO();
            donation.setDonationId(resultSet.getInt("donation_id"));
            donation.setAmount(resultSet.getDouble("amount"));
            donation.setDonorName(resultSet.getString("donor_name"));
            donation.setDate(resultSet.getDate("date"));
            donations.add(donation);
        }
        return donations;
    }
}
