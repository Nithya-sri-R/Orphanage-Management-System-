package Models;

import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.HealthMonitorDTO;

public class HealthMonitorDAO extends Connect {
    private static Connection connection;
    public HealthMonitorDAO(Connection connection) {
        this.connection=connection;
    }

    public void addHealthRecord(HealthMonitorDTO healthRecord) throws SQLException {
        String sql = "INSERT INTO health_records (orphan_id, blood_pressure, height, weight, condition, record_date, doctor_notes, medication) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, healthRecord.getOrphanId());
            statement.setInt(2, healthRecord.getBloodPressure());
            statement.setInt(3, healthRecord.getHeight());
            statement.setInt(4, healthRecord.getWeight());
            statement.setString(5, healthRecord.getCondition());
            statement.setDate(6, healthRecord.getRecordDate());
            statement.setString(7, healthRecord.getDoctorNotes());
            statement.setString(8, healthRecord.getMedication());
            statement.executeUpdate();
        }
    }

    public void updateHealthRecord(int healthRecordId, HealthMonitorDTO updatedRecord) throws SQLException {
        String sql = "UPDATE health_records SET blood_pressure = ?, height = ?, weight = ?, condition = ?, record_date = ?, doctor_notes = ?, medication = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, updatedRecord.getBloodPressure());
            statement.setInt(2, updatedRecord.getHeight());
            statement.setInt(3, updatedRecord.getWeight());
            statement.setString(4, updatedRecord.getCondition());
            statement.setDate(5, updatedRecord.getRecordDate());
            statement.setString(6, updatedRecord.getDoctorNotes());
            statement.setString(7, updatedRecord.getMedication());
            statement.setInt(8, healthRecordId);
            statement.executeUpdate();
        }
    }

    public void deleteHealthRecord(int healthRecordId) throws SQLException {
        String sql = "DELETE FROM health_records WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, healthRecordId);
            statement.executeUpdate();
        }
    }

    public List<HealthMonitorDTO> getAllHealthRecords() throws SQLException {
        List<HealthMonitorDTO> healthRecords = new ArrayList<>();
        String sql = "SELECT * FROM health_records";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HealthMonitorDTO record = new HealthMonitorDTO();
                record.setHealthRecordId(resultSet.getInt("id"));
                record.setOrphanId(resultSet.getInt("orphan_id"));
                record.setBloodPressure(resultSet.getInt("blood_pressure"));
                record.setHeight(resultSet.getInt("height"));
                record.setWeight(resultSet.getInt("weight"));
                record.setCondition(resultSet.getString("condition"));
                record.setRecordDate(resultSet.getDate("record_date"));
                record.setDoctorNotes(resultSet.getString("doctor_notes"));
                record.setMedication(resultSet.getString("medication"));
                healthRecords.add(record);
            }
        }
        return healthRecords;
    }
    public  List<HealthMonitorDTO> getHealthRecordsForOrphan(int orphanId) throws SQLException {
        List<HealthMonitorDTO> healthRecords = new ArrayList<>();
        String sql = "SELECT * FROM health_monitor WHERE orphan_id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orphanId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HealthMonitorDTO healthRecord = new HealthMonitorDTO();
                healthRecord.setHealthRecordId(resultSet.getInt("health_record_id"));
                healthRecord.setOrphanId(resultSet.getInt("orphan_id"));
                healthRecord.setHealthStatus(resultSet.getString("health_status"));
                healthRecord.setDoctorName(resultSet.getString("doctor_name"));
                healthRecord.setCheckupDate(resultSet.getDate("checkup_date").toLocalDate());
                healthRecords.add(healthRecord);
            }
        }
        return healthRecords;
    }
}
