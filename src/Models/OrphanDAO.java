package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.OrphanDTO;

public class OrphanDAO {
    private Connection connection;
    private static OrphanDAO instance;

    private OrphanDAO(Connection connection) {
        this.connection = connection;
    }

    public static synchronized OrphanDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new OrphanDAO(connection);
        }
        return instance;
    }

    public int addOrphan(OrphanDTO orphan) throws SQLException {
        String sql = "INSERT INTO orphans (orphanage_id, name, age, gender, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, orphan.getOrphanageId());
            statement.setString(2, orphan.getName());
            statement.setInt(3, orphan.getAge());
            statement.setString(4, orphan.getGender());
            statement.setString(5, orphan.getStatus());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the ID of the newly added orphan
                }
            }
            return -1; // Return -1 to indicate failure
        }
    }

    public List<OrphanDTO> getAllOrphans() throws SQLException {
        List<OrphanDTO> orphans = new ArrayList<>();
        String sql = "SELECT * FROM orphans";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrphanDTO orphan = new OrphanDTO();
                orphan.setOrphanId(resultSet.getInt("orphan_id"));
                orphan.setOrphanageId(resultSet.getInt("orphanage_id"));
                orphan.setName(resultSet.getString("name"));
                orphan.setAge(resultSet.getInt("age"));
                orphan.setGender(resultSet.getString("gender"));
                orphan.setStatus(resultSet.getString("status"));
                orphans.add(orphan);
            }
        }
        return orphans;
    }

    public void deleteOrphan(int orphanId) throws SQLException {
        String sql = "DELETE FROM orphans WHERE orphan_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orphanId);
            statement.executeUpdate();
        }
    }

    public void updateOrphanage(int orphanId, int orphanageId) throws SQLException {
        String sql = "UPDATE orphans SET orphanage_id = ? WHERE orphan_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orphanageId);
            statement.setInt(2, orphanId);
            statement.executeUpdate();
        }
    }

    public void updateOrphanGender(int orphanId, String gender) throws SQLException {
        String sql = "UPDATE orphans SET gender = ? WHERE orphan_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gender);
            statement.setInt(2, orphanId);
            statement.executeUpdate();
        }
    }

    public OrphanDTO getOrphanDetails(int orphanId) throws SQLException {
        OrphanDTO orphan = null;
        String sql = "SELECT * FROM orphans WHERE orphan_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orphanId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orphan = new OrphanDTO();
                orphan.setOrphanId(resultSet.getInt("orphan_id"));
                orphan.setOrphanageId(resultSet.getInt("orphanage_id"));
                orphan.setName(resultSet.getString("name"));
                orphan.setAge(resultSet.getInt("age"));
                orphan.setGender(resultSet.getString("gender"));
                orphan.setStatus(resultSet.getString("status"));
            }
        }
        return orphan;
    }
}
