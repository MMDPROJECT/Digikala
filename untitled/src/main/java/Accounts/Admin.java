package Accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static Database_Insert.Connect.connect;

public class Admin extends Account {
    private final String username;
    private final String password;
    private final String address;

    //Constructor

    public Admin(String username, String password, String address) {
        super();
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public Admin(UUID id, String username, String password, String address) {
        super(id);
        this.username = username;
        this.password = password;
        this.address = address;
    }

    //Getters and Setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    //Override

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }

    //Polymorphism


    @Override
    public boolean accountLogin(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    @Override
    public boolean doesAccountExist(String username) {
        return this.username.equalsIgnoreCase(username);
    }

    public static void insert(UUID accountID, String username, String password, String address) {
        String sql = "INSERT INTO Admins(AccountID, username, password, address) VALUES(?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountID.toString());
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
