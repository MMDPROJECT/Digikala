package Accounts;

import Categories.Product;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;

import java.util.HashMap;
import java.util.UUID;

public class User extends Account {
    private final String username;
    private final HashMap<UUID, ShoppingCart> carts;
    private final HashMap<UUID, Order> orders;
    private final HashMap<UUID, WalletReq> walletRequests;
    private final HashMap<UUID, Product> purchasedProducts;
    private ShoppingCart currentCart;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private double wallet;

    //Constructor

    public User(String username, String password, String email, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = 0;
        this.carts = new HashMap<>();
        this.orders = new HashMap<>();
        this.walletRequests = new HashMap<>();
        this.purchasedProducts = new HashMap<>();
        this.currentCart = null;
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

    public String getPhoneNumber() {
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

    public HashMap<UUID, WalletReq> getWalletRequests() {
        return walletRequests;
    }

    public HashMap<UUID, Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public ShoppingCart getCurrentCart() {
        return currentCart;
    }

    public void setCurrentCart(UUID cartID) {
        if (this.carts.containsKey(cartID)) {
            this.currentCart = carts.get(cartID);
            System.out.println("Cart has been successfully switched to " + cartID + "\n");
        } else {
            System.out.println("Cart has not been found!\n");
        }
    }

    public ShoppingCart getCart(UUID id) {
        return carts.get(id);
    }

    //Override


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", carts=" + carts +
                ", orders=" + orders +
                ", walletRequests=" + walletRequests +
                ", purchasedProducts=" + purchasedProducts +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", wallet=" + wallet +
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

    //Cart - Related Methods

    public void viewCarts() {
        if (carts.size() == 0) {
            System.out.println("No cart has been created yet!\n");
        } else {
            for (UUID id : carts.keySet()) {
                System.out.println(carts.get(id));
            }
        }
    }

    public void viewCart(UUID id) {
        if (carts.containsKey(id)) {
            System.out.println(carts.get(id));
        } else {
            System.out.println("Cart has not been found!\n");
        }
    }

    public void addCart(ShoppingCart cart) {
        carts.put(cart.getId(), cart);
    }

    public void checkOutCart(UUID id) {
        if (carts.containsKey(id)) {
            this.orders.put(id, new Order(carts.get(id).getName(), carts.get(id).getProducts(), carts.get(id).getItemNumber(), carts.get(id).getTotalPrice(), this));
        }
        System.out.println("Order has been successfully requested!\n");
    }

    //Order - Related Methods

    public void showAllCheckoutRequests() {
        if (orders.size() == 0) {
            System.out.println("No Checkout Request has been submitted yet!\n");
        } else {
            for (UUID id : orders.keySet()) {
                System.out.println(orders.get(id));
            }
        }
    }

    public void showConfirmedCheckoutRequests(){
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()){
            if (order.isConfirmed()){
                hasFoundAny = true;
                System.out.println(order);
            }
        }
        if (!hasFoundAny){
            System.out.println("No Checkout request has been found!\n");
        }
    }

    public void showUnconfirmedCheckoutRequests(){
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()){
            if (!order.isConfirmed()){
                hasFoundAny = true;
                System.out.println(order);
            }
        }
        if (!hasFoundAny){
            System.out.println("No Checkout request has been found!\n");
        }
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    //Wallet - Related Methods

    public void sendAWalletRequest(double value) {
        WalletReq walletRequest = new WalletReq(value, this);
        this.walletRequests.put(walletRequest.getId(), walletRequest);
        System.out.println("Wallet request has been successfully sent!\n");
    }

    public void showAllWalletRequests() {
        if (walletRequests.size() == 0) {
            System.out.println("No wallet request has been submitted!\n");
        } else {
            for (WalletReq walletRequest : walletRequests.values()) {
                System.out.println(walletRequest);
            }
        }
    }

    public void showConfirmedWalletRequests() {
        boolean hasFoundAny = false;
        for (WalletReq walletReq : walletRequests.values()) {
            if (walletReq.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(walletReq);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No wallet request has been found!\n");
        }
    }

    public void showUnconfirmedWalletRequests() {
        boolean hasFoundAny = false;
        for (WalletReq walletReq : walletRequests.values()) {
            if (!walletReq.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(walletReq);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No wallet request has been found!\n");
        }
    }

    public void addWallet(double wallet) {
        this.wallet += wallet;
    }

    //User - Related Methods

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password has been successfully edited!\n");
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
        System.out.println("Email has been successfully edited!\n");
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
        System.out.println("Phone Number has been successfully edited!\n");
    }

    public void updateAddress(String newAddress) {
        this.address = newAddress;
        System.out.println("Address has been successfully edited!\n");
    }

    //Product - related Methods
    public void addPurchasedProduct(Product product) {
        purchasedProducts.put(product.getId(), product);
    }

}