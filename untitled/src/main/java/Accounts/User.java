package Accounts;

import Categories.Product;
import Shop.Shop;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
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

    public User(UUID accountID, String username, HashMap<UUID, ShoppingCart> carts, HashMap<UUID, Order> orders, HashMap<UUID, WalletReq> walletRequests, HashMap<UUID, Product> purchasedProducts, String password, String email, UUID currentCartID, String phoneNumber, String address, double wallet) {
        super(accountID);
        this.username = username;
        this.carts = carts;
        this.orders = orders;
        this.walletRequests = walletRequests;
        this.purchasedProducts = purchasedProducts;
        this.password = password;
        this.email = email;
        this.currentCartID = currentCartID;
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
            JSONObject cartsJson = new JSONObject();
            JSONObject ordersJson = new JSONObject();
            JSONObject walletRequestJson = new JSONObject();
            JSONObject purchasedProductsJson = new JSONObject();
            cartsJson.put("carts", carts.keySet());
            ordersJson.put("orders", orders.keySet());
            walletRequestJson.put("walletRequests", walletRequests.keySet());
            purchasedProductsJson.put("purchasedProducts", purchasedProducts.keySet());
            pstmt.setString(9, cartsJson.toString());
            pstmt.setString(10, ordersJson.toString());
            pstmt.setString(11, walletRequestJson.toString());
            pstmt.setString(12, purchasedProductsJson.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWallet() {
        return wallet;
    }

    public HashMap<UUID, ShoppingCart> getCarts() {
        return carts;
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

    public void addCart(String cartName) {
        ShoppingCart shoppingCart = new ShoppingCart(cartName);
        this.carts.put(shoppingCart.getCartID(), shoppingCart);
        System.out.println("Cart has been successfully added!\n");
        updateUserInDatabase();
    }

    //Existence - Related methods

    public boolean doesCartExist(UUID cartID) {
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

    public void updateUserInDatabase() {
        String sql = "UPDATE Users SET password = ?, email = ?, currentCartID = ?, phoneNumber = ?, address = ?, wallet = ?, carts = ?, orders = ?, walletRequests = ?, purchasedProducts = ? WHERE AccountID = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, email);
            if (currentCartID != null) {
                pstmt.setString(3, currentCartID.toString());
            } else {
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

    public static void loadUsersFromDatabase(Shop shop) {
        String sql = "SELECT * FROM Users";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                UUID accountID = UUID.fromString(rs.getString("AccountID"));
                String username = rs.getString("username");
                HashMap<UUID, ShoppingCart> carts = new HashMap<>();
                HashMap<UUID, Order> orders = new HashMap<>();
                HashMap<UUID, WalletReq> walletRequests = new HashMap<>();
                HashMap<UUID, Product> purchasedProducts = new HashMap<>();
                String password = rs.getString("password");
                String email = rs.getString("email");
                UUID currentCartID = UUID.fromString(rs.getString("currentCartID"));
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                double wallet = rs.getDouble("wallet");
                JSONObject jsonCarts = new JSONObject(rs.getString("carts"));
                JSONObject jsonOrders = new JSONObject(rs.getString("orders"));
                JSONObject jsonWalletRequests = new JSONObject(rs.getString("walletRequests"));
                JSONObject jsonPurchasedProducts = new JSONObject(rs.getString("purchasedProducts"));
                JSONArray jsonArrayCarts = jsonCarts.getJSONArray("carts");
                JSONArray jsonArrayOrders = jsonOrders.getJSONArray("orders");
                JSONArray jsonArrayWalletRequests = jsonWalletRequests.getJSONArray("walletRequests");
                JSONArray jsonArrayPurchasedProducts = jsonPurchasedProducts.getJSONArray("purchasedProducts");
                for (int i = 0; i < jsonArrayCarts.length(); i++) {
                    ShoppingCart cart = shop.getCart(UUID.fromString(jsonArrayCarts.getString(i)));
                    carts.put(cart.getCartID(), cart);
                }
                for (int i = 0; i < jsonArrayOrders.length(); i++) {
                    Order order = shop.getOrder(UUID.fromString(jsonArrayOrders.getString(i)));
                    orders.put(order.getOrderID(), order);
                }
                for (int i = 0; i < jsonArrayWalletRequests.length(); i++) {
                    WalletReq walletReq = shop.getWalletRequest(UUID.fromString(jsonArrayWalletRequests.getString(i)));
                    walletRequests.put(walletReq.getWalletID(), walletReq);
                }
                for (int i = 0; i < jsonArrayPurchasedProducts.length(); i++) {
                    Product product = shop.getProduct(UUID.fromString(jsonArrayPurchasedProducts.getString(i)));
                    purchasedProducts.put(product.getProductID(), product);
                }
                User newUser = new User(accountID, username, carts, orders, walletRequests, purchasedProducts, password, email, currentCartID, phoneNumber, address, wallet);
                shop.userSignUp(newUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}