package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Resources.LoginDTO;

public class LoginDAO extends Connect {
    private static LoginDAO instance;
    private PreparedStatement getLogin;

    private LoginDAO() throws SQLException {
        getLogin = con.prepareStatement("SELECT L.EMAIL, L.Password, L.User_id, U.Role FROM login L JOIN Users U USING(User_id) WHERE L.email=?");
    }

    public static LoginDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new LoginDAO();
        }
        return instance;
    }

    public LoginDTO getLoginData(String email) throws SQLException {
        getLogin.setString(1, email);
        ResultSet res = getLogin.executeQuery();
        if (res.next()) {
            return new LoginDTO(res.getInt(3), res.getString(1), res.getString(2), res.getString(4));
        }
        return null;
    }
}
