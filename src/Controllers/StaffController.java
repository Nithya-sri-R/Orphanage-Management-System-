package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Models.DonationDAO;
import Models.HealthMonitorDAO;
import Models.OrphanDAO;
import Models.StaffDAO;
import Resources.DonationDTO;
import Resources.HealthMonitorDTO;
import Resources.OrphanDTO;
import Resources.StaffDTO;

public class StaffController {
    private Connection connection;
    private HealthMonitorDAO healthMonitorDAO;
    private OrphanDAO orphanDAO;
    private StaffDAO staffDAO;
    private DonationDAO donationDAO;

    public StaffController(Connection connection) {
        this.connection = connection;
        this.healthMonitorDAO = new HealthMonitorDAO(connection);
        try {
            this.orphanDAO = OrphanDAO.getInstance(connection);
            this.staffDAO = StaffDAO.getInstance(connection);
            this.donationDAO = DonationDAO.getInstance(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle initialization exception
        }
    }

    public void addDonation(DonationDTO donation) throws SQLException {
        donationDAO.addDonation(donation);
    }

    public List<DonationDTO> viewDonations() throws SQLException {
        return donationDAO.getAllDonations();
    }

    public void addOrphan(OrphanDTO orphan) throws SQLException {
        orphanDAO.addOrphan(orphan);
    }

    public void addStaff(StaffDTO staff) throws SQLException {
        staffDAO.addStaff(staff);
    }

    public List<OrphanDTO> getAllOrphans() throws SQLException {
        return orphanDAO.getAllOrphans();
    }

    public OrphanDTO getOrphanDetails(int orphanId) throws SQLException {
        return orphanDAO.getOrphanDetails(orphanId);
    }

    public List<StaffDTO> getAllStaff() throws SQLException {
        return staffDAO.getAllStaff();
    }

    public List<DonationDTO> getAllDonations() throws SQLException {
        return donationDAO.getAllDonations();
    }

    public List<HealthMonitorDTO> getHealthRecordsForOrphan(int orphanId) throws SQLException {
        return healthMonitorDAO.getHealthRecordsForOrphan(orphanId);
    }

    // Additional methods for updating, deleting, and retrieving staff and donations
}
