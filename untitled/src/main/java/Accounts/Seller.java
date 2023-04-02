package Accounts;

import Categories.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static Database_Insert.Connect.connect;

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

    public void addProduct(Product product) {
        this.availableProducts.put(product.getProductID(), product);
        System.out.println("Product has been successfully added!\n");
    }

    public void removeProduct(UUID id) {
        this.availableProducts.remove(id);
    }

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

    public void viewWallet() {
        System.out.println("Current Wallet : " + this.wallet);
    }

    public void addSellerCut(double value) {
        this.wallet += value;
    }

    public static void insert(UUID accountID, String companyName, String password, Set<UUID> products, double wallet, boolean isAuthorized) {
        String sql = "INSERT INTO Sellers(AccountID, companyName, password, availableProducts, wallet, isAuthorized) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountID.toString());
            pstmt.setString(2, companyName);
            pstmt.setString(3, password);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("availableProducts", new JSONArray(products));
            String availableProducts = jsonObject.toString();
            pstmt.setString(4, availableProducts);
            pstmt.setDouble(5, wallet);
            pstmt.setString(6, Boolean.toString(isAuthorized));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
