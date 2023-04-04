package Accounts;

import Categories.Product;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

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

    public UUID getCurrentCartID() {
        return currentCartID;
    }

    public void setCurrentCart(UUID cartID) {
        if (doesCartExist(cartID)) {
            this.currentCartID = cartID;
            System.out.println("Cart has been successfully switched to " + cartID + "\n");
            updateUserInDatabase();
        } else {
            System.out.println("Cart has not been found!\n");
        }
    }
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

    //Login - Related methods


    @Override
    public boolean accountLogin(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equals(password);
    }

    //Search & Show - Related methods

    public void showPurchasedProducts() {
        if (this.purchasedProducts.size() == 0) {
            System.out.println("You haven't purchased any product yet!\n");
        } else {
            for (Product product : this.purchasedProducts.values()) {
                System.out.println(product);
            }
        }
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

    public void viewCart(UUID cartID) {
        if (doesCartExist(cartID)) {
            System.out.println(getCart(cartID));
        } else {
            System.out.println("Cart has not been found!\n");
        }
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

    public void showAllWalletRequests() {
        if (walletRequests.size() == 0) {
            System.out.println("No wallet request has been submitted!\n");
        } else {
            for (WalletReq walletRequest : walletRequests.values()) {
                System.out.println(walletRequest);
            }
        }
    }

    public void viewWallet() {
        System.out.println("Current wallet: " + this.getWallet() + "\n");
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

    //Cart - Related methods

    public void addCart(String  cartName) {
        ShoppingCart shoppingCart = new ShoppingCart(cartName);
        this.carts.put(shoppingCart.getCartID(), shoppingCart);
        System.out.println("Cart has been successfully added!\n");
        updateUserInDatabase();
    }

    //Existence - Related methods

    public boolean doesCartExist(UUID cartID){
        return this.carts.containsKey(cartID);
    }

    public boolean hasSelectedCart() {
        return this.currentCartID != null;
    }

    @Override
    public boolean doesAccountExist(String username) {
        return this.username.equalsIgnoreCase(username);
    }

    public boolean isProductPurchased(UUID id) {
        return this.purchasedProducts.containsKey(id);
    }

    //Cart - Related methods

    public Order checkoutCart(UUID cartID) {
        this.getCart(cartID).checkoutCart();
        Order order = new Order(this.getCart(cartID).getName(), this.getCart(cartID).getProducts(), this.getCart(cartID).getItemNumber(), this.getCart(cartID).getTotalPrice(), this.getAccountID());
        this.addOrder(order);
        return order;
    }

    //Order - Related Methods

    public void addOrder(Order order) {
        this.orders.put(order.getOrderID(), order);
        updateUserInDatabase();
    }

    public boolean checkBuyerPocket(double value) {
        return this.wallet >= value;
    }

    public void buyerPayOff(double value) {
        System.out.println("Money has been successfully paid off!\n");
        this.wallet -= value;
        updateUserInDatabase();
    }

    public void addPurchasedProduct(Product product) {
        purchasedProducts.put(product.getProductID(), product);
        updateUserInDatabase();
    }

    //Wallet - Related Methods

    public void submitAWalletRequest(WalletReq walletReq) {
        this.walletRequests.put(walletReq.getWalletID(), walletReq);
        System.out.println("Wallet request has been successfully sent!\n");
    }

    //User - Related Methods

    public void addWallet(double wallet) {
        this.wallet += wallet;
        updateUserInDatabase();
    }

    public void updatePassword(String newPassword) {
        this.setPassword(newPassword);
        updateUserInDatabase();
        System.out.println("Password has been successfully edited!\n");
    }

    public void updateEmail(String newEmail) {
        this.setEmail(newEmail);
        updateUserInDatabase();
        System.out.println("Email has been successfully edited!\n");
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.setPhoneNumber(newPhoneNumber);
        updateUserInDatabase();
        System.out.println("Phone Number has been successfully edited!\n");
    }

    public void updateAddress(String newAddress) {
        this.setAddress(newAddress);
        updateUserInDatabase();
        System.out.println("Address has been successfully edited!\n");
    }

    //Database - Related methods

    public void updateUserInDatabase(){
        String sql = "UPDATE Users SET password = ?, email = ?, currentCartID = ?, phoneNumber = ?, address = ?, wallet = ?, carts = ?, orders = ?, walletRequests = ?, purchasedProducts = ? WHERE AccountID = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, email);
            if (currentCartID != null){
                pstmt.setString(3, currentCartID.toString());
            }
            else {
                pstmt.setString(3, null);
            }
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, address);
            pstmt.setDouble(6, wallet);
            JSONObject cartsJson = new JSONObject();
            JSONObject ordersJson = new JSONObject();
            JSONObject walletRequestJson = new JSONObject();
            JSONObject purchasedProductsJson = new JSONObject();
            cartsJson.put("carts", carts.keySet());
            ordersJson.put("orders", orders.keySet());
            walletRequestJson.put("walletRequests", walletRequests.keySet());
            purchasedProductsJson.put("purchasedProducts", purchasedProducts.keySet());
            pstmt.setString(7, cartsJson.toString());
            pstmt.setString(8, ordersJson.toString());
            pstmt.setString(9, walletRequestJson.toString());
            pstmt.setString(10, purchasedProductsJson.toString());
            pstmt.setString(11, getAccountID().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}