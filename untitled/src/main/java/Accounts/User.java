package Accounts;

import Categories.Product;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static Connection.Connect.connect;

public class User extends Account {
    private final String username;
    private final HashMap<UUID, ShoppingCart> carts;
    private final HashMap<UUID, Order> orders;
    private final HashMap<UUID, WalletReq> walletRequests;
    private final HashMap<UUID, Product> purchasedProducts;
    private String password;
    private String email;
    private UUID currentCartID;
    private String phoneNumber;
    private String address;
    private double wallet;

    //Constructor

    public User(String username, String password, String email, String phoneNumber, String address) {
        super();
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
        this.currentCartID = null;
        insert();
    }

    public User(UUID id, String username, HashMap<UUID, ShoppingCart> carts, HashMap<UUID, Order> orders, HashMap<UUID, WalletReq> walletRequests, HashMap<UUID, Product> purchasedProducts, UUID currentCartID, String password, String email, String phoneNumber, String address, double wallet) {
        super(id);
        this.username = username;
        this.carts = carts;
        this.orders = orders;
        this.walletRequests = walletRequests;
        this.purchasedProducts = purchasedProducts;
        this.currentCartID = currentCartID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = wallet;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Users(AccountID, username, password, email, currentCartID, phoneNumber, address, wallet, carts, orders, walletRequests, purchasedProducts) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, getAccountID().toString());
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setString(5, null);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, address);
            pstmt.setDouble(8, wallet);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String cartsJson = objectMapper.writeValueAsString(this.carts.keySet());
                String ordersJson = objectMapper.writeValueAsString(this.orders.keySet());
                String walletRequestsJson = objectMapper.writeValueAsString(this.walletRequests.keySet());
                String purchasedProductsJson = objectMapper.writeValueAsString(this.purchasedProducts.keySet());
                pstmt.setString(9, cartsJson);
                pstmt.setString(10, ordersJson);
                pstmt.setString(11, walletRequestsJson);
                pstmt.setString(12, purchasedProductsJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
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

    public UUID getCurrentCart() {
        return currentCartID;
    }

    public void setCurrentCart(UUID cartID) {
        if (this.carts.containsKey(cartID)) {
            this.currentCartID = cartID;
            System.out.println("Cart has been successfully switched to " + cartID + "\n");
            this.switchCartDatabase(cartID);
        } else {
            System.out.println("Cart has not been found!\n");
        }
    }

    //Override

    public ShoppingCart getCart(UUID id) {
        return carts.get(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", carts=" + carts +
                ", orders=" + orders +
                ", walletRequests=" + walletRequests +
                ", purchasedProducts=" + purchasedProducts +
                ", currentCartID=" + currentCartID +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", wallet=" + wallet +
                "} " + super.toString();
    }

    @Override
    public boolean accountLogin(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    //Cart - Related Methods

    @Override
    public boolean doesAccountExist(String username) {
        return this.username.equalsIgnoreCase(username);
    }

    public Order checkoutCart(UUID cartID) {
        this.getCart(cartID).checkoutCart();
        Order order = new Order(this.getCart(cartID).getName(), this.getCart(cartID).getProducts(), this.getCart(cartID).getItemNumber(), this.getCart(cartID).getTotalPrice(), this.getAccountID());
        this.addOrder(order);
        return order;
    }

    public void viewCarts() {
        if (this.carts.size() == 0) {
            System.out.println("No cart has been created yet!\n");
        } else {
            for (ShoppingCart cart : this.carts.values()) {
                System.out.println(cart);
            }
        }
    }

    public void viewCart(UUID id) {
        if (this.carts.containsKey(id)) {
            System.out.println(this.carts.get(id));
        } else {
            System.out.println("Cart has not been found!\n");
        }
    }

    public void addCart(ShoppingCart cart) {
        this.carts.put(cart.getOrderID(), cart);
        System.out.println("Cart has been successfully added!\n");
    }

    //Order - Related Methods

    public boolean hasSelectedCart() {
        return this.currentCartID != null;
    }

    public void showAllOrders() {
        if (this.orders.size() == 0) {
            System.out.println("No Checkout Request has been submitted yet!\n");
        } else {
            for (Order order : this.orders.values()) {
                System.out.println(order);
            }
        }
    }

    public void showConfirmedOrders() {
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()) {
            if (order.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(order);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Checkout request has been found!\n");
        }
    }

    public void showUnconfirmedOrders() {
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()) {
            if (!order.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(order);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Checkout request has been found!\n");
        }
    }

    public boolean checkBuyerPocket(double value) {
        return this.wallet >= value;
    }

    public void buyerPayOff(double value) {
        System.out.println("Money has been successfully paid off!\n");
        this.wallet -= value;
        updateUserWalletInDatabase();
    }

    //Wallet - Related Methods

    public void addOrder(Order order) {
        this.orders.put(order.getOrderID(), order);
    }

    public void viewWallet() {
        System.out.println("Current wallet: " + this.getWallet() + "\n");
    }

    public void submitAWalletRequest(WalletReq walletReq) {
        this.walletRequests.put(walletReq.getWalletID(), walletReq);
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

    //User - Related Methods

    public void addWallet(double wallet) {
        this.wallet += wallet;
    }

    public void updatePassword(String newPassword) {
        this.setPassword(newPassword);
        System.out.println("Password has been successfully edited!\n");
    }

    public void updateEmail(String newEmail) {
        this.setEmail(newEmail);
        System.out.println("Email has been successfully edited!\n");
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone Number has been successfully edited!\n");
    }

    public void updateAddress(String newAddress) {
        this.setAddress(newAddress);
        System.out.println("Address has been successfully edited!\n");
    }

    public void showPurchasedProducts() {
        if (this.purchasedProducts.size() == 0) {
            System.out.println("You haven't purchased any product yet!\n");
        } else {
            for (Product product : this.purchasedProducts.values()) {
                System.out.println(product);
            }
        }
    }

    public boolean isProductPurchased(UUID id) {
        return this.purchasedProducts.containsKey(id);
    }

    //Product - related Methods
    public void addPurchasedProduct(Product product) {
        purchasedProducts.put(product.getProductID(), product);
    }

    //Database - Related methods

    public void switchCartDatabase(UUID cartID) {
        String sql = "UPDATE Users SET currentCartID = ? WHERE AccountID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cartID.toString());
            stmt.setString(2, getAccountID().toString());
            stmt.executeUpdate();
            System.out.println("Current cart has been successfully updated in DataBase!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUserWalletInDatabase() {
        String sql = "UPDATE Users SET wallet = ? WHERE AccountID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, this.getWallet());
            stmt.setString(2, getAccountID().toString());
            stmt.executeUpdate();
            System.out.println("User's wallet has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}