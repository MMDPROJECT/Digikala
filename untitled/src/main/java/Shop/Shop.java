package Shop;

import Accounts.Account;
import Accounts.Admin;
import Categories.Product;
import Shopping.Order;
import Shopping.WalletReq;

import java.util.HashMap;
import java.util.UUID;

public class Shop {
    private String name;
    private String webAddress;
    private String supportPhoneNumber;
    private HashMap<UUID, Account> accounts;
    private HashMap<UUID, Product> products;
    private HashMap<UUID, Order> orders;
    private HashMap<UUID, WalletReq> walletRequests;
    private double totalGained;

    //Constructor

    public Shop(String name, String webAddress, String supportPhoneNumber) {
        this.name = name;
        this.webAddress = webAddress;
        this.supportPhoneNumber = supportPhoneNumber;
        this.totalGained = 0;
        this.accounts = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
        this.walletRequests = new HashMap<>();
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getSupportPhoneNumber() {
        return supportPhoneNumber;
    }

    public HashMap<UUID, Account> getAccounts() {
        return accounts;
    }

    public HashMap<UUID, Product> getProducts() {
        return products;
    }

    public HashMap<UUID, Order> getOrders() {
        return orders;
    }

    public double getTotalGained() {
        return totalGained;
    }

    public HashMap<UUID, WalletReq> getWalletRequests() {
        return walletRequests;
    }

    //Admin - Related Methods

    public boolean doesAccountExist(UUID id){
        //TODO
        return true;
    }

    public boolean doesAccountExist(String username){
        //TODO
        return true;
    }

    public boolean adminLogin(String username, String password){
        for (UUID id : accounts.keySet()){
            if (accounts.get(id) instanceof Admin){
                if (((Admin) accounts.get(id)).getUsername().equals(username) && ((Admin) accounts.get(id)).getPassword().equals(password)){
                    System.out.println("Admin has been successfully logged in!\n");
                    return true;
                }
            }
        }
        System.out.println("Admin not found!\n");
        return false;
    }

    public void adminSignUp(){
        //TODO
    }

    public void walletConfirm(){
        //TODO
    }

    public void orderConfirm(){
        //TODO
    }

    public void sellerAuthorization(){
        //TODO
    }

    //User - Related Methods

    public void userLogin
}
