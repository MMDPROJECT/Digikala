package Accounts;

import Categories.Product;

import java.util.HashMap;
import java.util.UUID;

public class Seller extends Account {
    private final String companyName;
    private final String password;
    private final HashMap<UUID, Product> availableProducts;
    private final double wallet;
    private boolean isAuthorized;

    //Constructor

    public Seller(String companyName, String password) {
        this.companyName = companyName;
        this.password = password;
        this.availableProducts = new HashMap<>();
        this.wallet = 0;
        this.isAuthorized = false;
    }


    //Getters and Setters

    public String getCompanyName() {
        return companyName;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<UUID, Product> getAvailableProducts() {
        return availableProducts;
    }

    public double getWallet() {
        return wallet;
    }

    @Override
    public String getUsername() {
        return this.companyName;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }
    //Override

    @Override
    public String toString() {
        return "Seller{" +
                "isAuthorized=" + isAuthorized +
                ", companyName='" + companyName + '\'' +
                ", password='" + password + '\'' +
                ", availableProducts=" + availableProducts +
                ", wallet=" + wallet +
                "} " + super.toString();
    }

    @Override
    public boolean accountLogin(String username, String password) {
        return this.companyName.equalsIgnoreCase(username) && this.password.equals(password);
    }

    @Override
    public boolean doesAccountExist(String username) {
        return this.companyName.equalsIgnoreCase(username);
    }

    //Seller - Related Methods

    public boolean doesProductExist(Product product) {
        for (UUID id : availableProducts.keySet()) {
            if (availableProducts.get(id).equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void viewAvailableProducts() {
        if (availableProducts.size() == 0) {
            System.out.println("No product has been added yet!\n");
        } else {
            for (UUID id : availableProducts.keySet()) {
                System.out.println(availableProducts.get(id));
            }
        }
    }

    public void authorizeSeller() {
        this.isAuthorized = true;
    }

    public void viewWallet(){
        System.out.println("Current Wallet : " + this.wallet);
    }
}
