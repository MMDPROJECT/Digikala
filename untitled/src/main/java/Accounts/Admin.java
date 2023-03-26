package Accounts;

public class Admin extends Account {
    private final String username;
    private final String password;
    private final String address;

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

    //Polymorphism


    @Override
    public boolean accountLogin(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    @Override
    public boolean doesAccountExist(String username) {
        return this.username.equalsIgnoreCase(username);
    }
}
