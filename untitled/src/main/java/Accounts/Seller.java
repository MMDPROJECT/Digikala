package Accounts;

import Categories.Product;
import Shop.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static Connection.Connect.connect;

public class Seller extends Account {
    private final String companyName;
    private final String password;
    private final HashMap<UUID, Product> availableProducts;
    private double wallet;
    private boolean isAuthorized;

    //Constructor

    public Seller(String companyName, String password) {
        super();
        this.companyName = companyName;
        this.password = password;
        this.availableProducts = new HashMap<>();
        this.wallet = 0;
        this.isAuthorized = false;
        insert();
    }

    public Seller(UUID id, String companyName, String password, HashMap<UUID, Product> availableProducts, double wallet, boolean isAuthorized) {
        super(id);
        this.companyName = companyName;
        this.password = password;
        this.availableProducts = availableProducts;
        this.wallet = wallet;
        this.isAuthorized = isAuthorized;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Sellers(AccountID, companyName, password, availableProducts, wallet, isAuthorized) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, getAccountID().toString());
            pstmt.setString(2, companyName);
            pstmt.setString(3, password);
            JSONObject jsonProducts = new JSONObject();
            jsonProducts.put("availableProducts", new JSONArray(this.availableProducts.keySet()));
            String availableProducts = jsonProducts.toString();
            pstmt.setString(4, availableProducts);
            pstmt.setDouble(5, wallet);
            pstmt.setString(6, Boolean.toString(isAuthorized));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getUsername() {
        return this.companyName;
    }
    //Override

    public boolean isAuthorized() {
        return isAuthorized;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "isAuthorized=" + isAuthorized +
                ", companyName='" + companyName + '\'' +
                "} " + super.toString();
    }

    //Login - Related methods

    @Override
    public boolean accountLogin(String username, String password) {
        return this.companyName.equalsIgnoreCase(username) && this.password.equals(password);
    }

    //Existence - Related methods

    @Override
    public boolean doesAccountExist(String username) {
        return this.companyName.equalsIgnoreCase(username);
    }

    public boolean isProductAvailable(UUID productID) {
        return this.availableProducts.containsKey(productID);
    }

    //Seller - Related Methods

    public void addProductToSellerProducts(Product product) {
        this.availableProducts.put(product.getProductID(), product);
        System.out.println("Product has been successfully added!\n");
    }

    public void removeProduct(UUID id) {
        this.availableProducts.remove(id);
        updateSellerInDatabase();
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
        updateSellerInDatabase();
    }

    public void viewWallet() {
        System.out.println("Current Wallet : " + this.wallet);
    }

    public void addSellerCut(double value) {
        this.wallet += value;
        updateSellerInDatabase();
    }

    //Database - Related methods

    public void updateSellerInDatabase() {
        String sql = "UPDATE Sellers SET availableProducts = ?, wallet = ?, isAuthorized = ? WHERE AccountID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("availableProducts", availableProducts.keySet());
            String availableProductsJson = jsonObject.toString();
            stmt.setString(1, availableProductsJson);
            stmt.setDouble(2, wallet);
            stmt.setString(3, Boolean.toString(isAuthorized));
            stmt.setString(4, getAccountID().toString());
            stmt.executeUpdate();
            System.out.println("Seller's wallet has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadSellersFromDatabase(Shop shop) {
        String sql = "SELECT * FROM Sellers";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                UUID accountID = UUID.fromString(rs.getString("AccountID"));
                String companyName = rs.getString("companyName");
                String password = rs.getString("password");
                double wallet = rs.getDouble("wallet");
                boolean isAuthorized = Boolean.parseBoolean(rs.getString("isAuthorized"));
                JSONObject jsonAvailableProducts = new JSONObject(rs.getString("availableProducts"));
                JSONArray jsonArray = jsonAvailableProducts.getJSONArray("availableProducts");
                HashMap<UUID, Product> availableProducts = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    UUID productID = UUID.fromString(jsonArray.getString(i));
                    availableProducts.put(productID, shop.getProduct(productID));
                }
                Seller newSeller = new Seller(accountID, companyName, password, availableProducts, wallet, isAuthorized);
                shop.sellerSignUp(newSeller);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
