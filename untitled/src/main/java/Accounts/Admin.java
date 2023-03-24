package Accounts;

import java.util.UUID;

public class Admin {
    private String username;
    private String password;
    private String address;
    private UUID id;

    //Constructor

    public Admin(String username, String password, String address) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    //Override

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
