package Resources;

public class OrphanDTO {
    private int orphanId;
    private int orphanageId;
    private String name;
    private int age;
    private String gender;
    private String status;
  

    public int getOrphanId() {
        return orphanId;
    }

    public void setOrphanId(int orphanId) {
        this.orphanId = orphanId;
    }

    public int getOrphanageId() {
        return orphanageId;
    }

    public void setOrphanageId(int orphanageId) {
        this.orphanageId = orphanageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
