package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Models.DonationDAO;
import Models.LoginDAO;
import Models.OrphanDAO;
import Models.UserDAO;
import Resources.DonationDTO;
import Resources.LoginDTO;
import Resources.OrphanDTO;
import Resources.UserDTO;
import util.Cookie;
public class UserControl {
    private Connection connection;
    public int login(String email, String password) throws Exception {
        LoginDAO login = LoginDAO.getInstance();
        LoginDTO user = login.getLoginData(email);
        if (user == null) {
            throw new Exception("Invalid user");
        }
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }
        Cookie.userId = user.getUserId();
        return user.getRole().equalsIgnoreCase("admin") ? 1 : 2;
    }

    public int signUp(String userName, String email, String password, String phoneNo, String role, String address) throws SQLException {
        UserDAO sign = UserDAO.getInstance();
        UserDTO dummy = new UserDTO();

        dummy.setAddress(address);
        dummy.setPhone(phoneNo);
        dummy.setRole(role);
        dummy.setName(userName);

        int user1 = sign.signUp(dummy);

        int j = sign.getUserId(phoneNo);

        LoginDTO tummy = new LoginDTO();
        tummy.setEmail(email);
        tummy.setPassword(password);

        int go = sign.getsignUp2(tummy, j);

        if (go == user1)
            return user1;
        return 2;
    }

    

    public List<OrphanDTO> getAllOrphans() throws SQLException {
        OrphanDAO orphanDAO = OrphanDAO.getInstance(connection);
        return orphanDAO.getAllOrphans();
    }
    public List<DonationDTO> getAllDonations()throws SQLException {
        DonationDAO donationDAO = DonationDAO.getInstance(connection);
        return donationDAO.getAllDonations();
    }

    public int addOrphan(String name, int age, String gender, String status) throws SQLException {
        OrphanDAO orphanDAO = OrphanDAO.getInstance(connection);

        // Create an OrphanDTO object with the provided parameters
        OrphanDTO orphanDTO = new OrphanDTO();
        orphanDTO.setName(name);
        orphanDTO.setAge(age);
        orphanDTO.setGender(gender);
        orphanDTO.setStatus(status);

        // Call the addOrphan method of OrphanDAO with the OrphanDTO object
        return orphanDAO.addOrphan(orphanDTO);
    }

    // Additional methods for orphan management, such as adding, deleting, or updating orphan records
}
