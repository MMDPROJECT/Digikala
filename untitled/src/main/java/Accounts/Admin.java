package Accounts;

import Shop.Shop;
import com.beust.ah.A;

import java.sql.*;
import java.util.UUID;

import static Connection.Connect.connect;

public class Admin extends Account {
    private final String username;
    private final String password;
    private final String email;

    //Constructor

    public Admin(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        insert();
    }

    public Admin(UUID id, String username, String password, String email) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Getters and Setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean accountLogin(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    @Override
    public boolean doesAccountExist(String username) {
        return this.username.equalsIgnoreCase(username);
    }


    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Admins(AccountID, username, password, email) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, getAccountID().toString());
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadAdminsFromDatabase(Shop shop){
        String sql = "SELECT * FROM Admins";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                UUID accountID = UUID.fromString(rs.getString("AccountID"));
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Admin newAdmin = new Admin(accountID, username, password, email);
                shop.adminSignUp(newAdmin);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
