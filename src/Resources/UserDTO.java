package Resources;

public class UserDTO {
    private int userId;
    private String name;
    private String phone;
    private String role;
    private String address;

    // Constructor
    public UserDTO(String name, String phone, String role, String address) {
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.address = address;
    }

    public UserDTO() {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // // Override toString method for debugging
    // @Override
    // public String toString() {
    //     return "UserDTO{" +
    //             "userId=" + userId +
    //             ", name='" + name + '\'' +
    //             ", phone='" + phone + '\'' +
    //             ", role='" + role + '\'' +
    //             ", address='" + address + '\'' +
    //             '}';
    // }
}
