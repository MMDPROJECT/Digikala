package Accounts;

import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;

import java.util.HashMap;
import java.util.UUID;

public class User extends Account {
    private String username;
    private String password;
    private String email;
    private int phoneNumber;
    private String address;
    private double wallet;
    private HashMap<UUID, ShoppingCart> carts;
    private HashMap<UUID, Order> orders;

    //Constructor

    public User(String username, String password, String email, int phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = 0;
        this.carts = new HashMap<>();
        this.orders = new HashMap<>();
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getWallet() {
        return wallet;
    }

    public HashMap<UUID, ShoppingCart> getCarts() {
        return carts;
    }

    public HashMap<UUID, Order> getOrders() {
        return orders;
    }

    //Override

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", wallet=" + wallet +
                ", carts=" + carts +
                ", orders=" + orders +
                "} " + super.toString();
    }

    //User - Related Methods

    public void viewCarts() {
        if (carts.size() == 0) {
            System.out.println("No cart has been created yet!\n");
        } else {
            for (UUID id : carts.keySet()) {
                System.out.println(carts.get(id));
            }
        }
    }

    public void viewOrders() {
        if (orders.size() == 0) {
            System.out.println("No order has been submitted yet!\n");
        } else {
            for (UUID id : orders.keySet()) {
                System.out.println(orders.get(id));
            }
        }
    }

    public void addCart(ShoppingCart cart){
        carts.put(cart.getId(), cart);
    }

    public void addOrder(Order order){
        orders.put(order.getId(), order);
    }

    public ShoppingCart getCart(UUID id){
        return carts.get(id);
    }

    public void chargeWallet(WalletReq wallet){
        this.wallet += wallet.getValue();
    }

    public void updatePassword(String newPassword){
        this.password = newPassword;
        System.out.println("Password has been successfully edited!\n");
    }

    public void updateEmail(String newEmail){
        this.email = newEmail;
        System.out.println("Email has been successfully edited!\n");
    }

    public void updatePhoneNumber(int newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
        System.out.println("Phone Number has been successfully edited!\n");
    }

    public void updateAddress(String newAddress){
        this.address = newAddress;
        System.out.println("Address has been successfully edited!\n");
    }
}