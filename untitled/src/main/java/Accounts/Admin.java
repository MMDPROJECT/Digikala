package Accounts;

import java.util.UUID;

public class Admin extends Account {
    private String username;
    private String password;
    private String address;

    //Constructor

    public Admin(String username, String password, String address) {
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
}
