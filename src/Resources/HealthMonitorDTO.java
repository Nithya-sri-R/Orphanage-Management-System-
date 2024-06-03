package Resources;

import java.sql.Date;
import java.time.LocalDate;

public class HealthMonitorDTO {
    private int healthRecordId;
    private int orphanId;
    private int bloodPressure;
    private int height;
    private int weight;
    private String condition;
    private Date recordDate;
    private String doctorNotes;
    private String medication;
    private String healthStatus;
    private String doctorName;
    private LocalDate checkupDate;

    public HealthMonitorDTO() {}

    public HealthMonitorDTO(int orphanId, int bloodPressure, int height, int weight, String condition,
            Date recordDate, String doctorNotes, String medication) {
        this.orphanId = orphanId;
        this.bloodPressure = bloodPressure;
        this.height = height;
        this.weight = weight;
        this.condition = condition;
        this.recordDate = recordDate;
        this.doctorNotes = doctorNotes;
        this.medication = medication;
    }

    public int getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(int healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public int getOrphanId() {
        return orphanId;
    }

    public void setOrphanId(int orphanId) {
        this.orphanId = orphanId;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public void setCheckupDate(LocalDate checkupDate) {
        this.checkupDate = checkupDate;
    }
}
