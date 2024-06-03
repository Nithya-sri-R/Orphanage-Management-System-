package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Resources.LoginDTO;
import Resources.UserDTO;

public class UserDAO extends Connect {
    private static UserDAO instance;
    private PreparedStatement signUpStatement;
    private PreparedStatement loginStatement;
    private PreparedStatement getUserIdStatement;

    private UserDAO() throws SQLException {
        signUpStatement = con.prepareStatement("INSERT INTO Users (User_Name, Phone_no, Role, Address) VALUES (?, ?, ?, ?)");
        loginStatement = con.prepareStatement("INSERT INTO login (Email, Password, User_id) VALUES (?, ?, ?)");
        getUserIdStatement = con.prepareStatement("SELECT User_id FROM Users WHERE Phone_no = ?");
    }

    public static UserDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public int signUp(UserDTO user) throws SQLException {
        signUpStatement.setString(1, user.getName());
        signUpStatement.setString(2, user.getPhone());
        signUpStatement.setString(3, user.getRole());
        signUpStatement.setString(4, user.getAddress());
        return signUpStatement.executeUpdate();
    }

    public int getUserId(String phoneNumber) throws SQLException {
        getUserIdStatement.setString(1, phoneNumber);
        ResultSet rs = getUserIdStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("User_id");
        } else {
            return 0; // Return 0 if no user found with the given phone number
        }
    }

    public int getsignUp2(LoginDTO tummy, int id) throws SQLException {
        loginStatement.setString(1, tummy.getEmail());
        loginStatement.setString(2, tummy.getPassword());
        loginStatement.setInt(3, id);
        return loginStatement.executeUpdate();
    }
}
