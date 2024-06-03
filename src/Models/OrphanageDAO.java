package Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.OrphanageDTO;

public class OrphanageDAO {
    private Connection connection;

    public OrphanageDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOrphanage(OrphanageDTO orphanage) throws SQLException {
        String sql = "INSERT INTO orphanages (name, address, contact_number) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orphanage.getName());
            statement.setString(2, orphanage.getAddress());
            statement.setString(3, orphanage.getContactNumber());
            statement.executeUpdate();
        }
    }

    public List<OrphanageDTO> getAllOrphanages() throws SQLException {
        List<OrphanageDTO> orphanages = new ArrayList<>();
        String sql = "SELECT * FROM orphanages";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrphanageDTO orphanage = new OrphanageDTO();
                orphanage.setId(resultSet.getInt("id"));
                orphanage.setName(resultSet.getString("name"));
                orphanage.setAddress(resultSet.getString("address"));
                orphanage.setContactNumber(resultSet.getString("contact_number"));
                orphanages.add(orphanage);
            }
        }
        return orphanages;
    }

    // Add other methods as needed, such as updateOrphanage, deleteOrphanage, etc.
}

