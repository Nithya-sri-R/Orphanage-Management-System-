package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.StaffDTO;

public class StaffDAO {
    private Connection connection;
    private static StaffDAO instance;

    private StaffDAO(Connection connection) {
        this.connection = connection;
    }

    public static synchronized StaffDAO getInstance(Connection connection) throws SQLException {
        if (instance == null) {
            instance = new StaffDAO(connection);
        }
        return instance;
    }

    public void addStaff(StaffDTO staff) throws SQLException {
        String sql = "INSERT INTO staff (staff_name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, staff.getStaffName());
            statement.setString(2, staff.getPosition());
            statement.setDouble(3, staff.getSalary());
            statement.executeUpdate();
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    staff.setStaffId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating staff failed, no ID obtained.");
                }
            }
        }
    }

    public List<StaffDTO> getAllStaff() throws SQLException {
        List<StaffDTO> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StaffDTO staff = new StaffDTO();
                staff.setStaffId(resultSet.getInt("staff_id"));
                staff.setStaffName(resultSet.getString("staff_name"));
                staff.setPosition(resultSet.getString("position"));
                staff.setSalary(resultSet.getDouble("salary"));
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public void updateStaff(StaffDTO staff) throws SQLException {
        String sql = "UPDATE staff SET staff_name = ?, position = ?, salary = ? WHERE staff_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, staff.getStaffName());
            statement.setString(2, staff.getPosition());
            statement.setDouble(3, staff.getSalary());
            statement.setInt(4, staff.getStaffId());
            statement.executeUpdate();
        }
    }

    public void deleteStaff(int staffId) throws SQLException {
        String sql = "DELETE FROM staff WHERE staff_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, staffId);
            statement.executeUpdate();
        }
    }
}
