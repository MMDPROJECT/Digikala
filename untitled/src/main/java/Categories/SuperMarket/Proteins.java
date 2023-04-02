package Categories.SuperMarket;

import Categories.SuperMarket.Enums.ProteinProductType;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Proteins extends SuperMarket {
    private final String brand;
    private final double protein;
    private final ProteinProductType productType;

    //Constructor

    public Proteins(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String brand, double protein, ProteinProductType productType) {
        super(name, color, quantity, price, sellerID, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.brand = brand;
        this.protein = protein;
        this.productType = productType;
    }

    public Proteins(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String brand, double protein, ProteinProductType productType) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.brand = brand;
        this.protein = protein;
        this.productType = productType;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> IngredientItems, String CountryOfOrigin, String brand, double protein, ProteinProductType productType) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, brand, protein, productType, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productID.toString());
            pstmt.setString(2, name);
            pstmt.setString(3, color);
            pstmt.setDouble(4, price);
            pstmt.setString(5, sellerID.toString());
            pstmt.setInt(6, quantity);
            JSONObject json1 = new JSONObject();
            json1.put("comments", new JSONArray(comments));
            String strComments = json1.toString();
            pstmt.setString(7, strComments);
            pstmt.setString(8, Boolean.toString(hasBox));
            pstmt.setDouble(9, weight);
            pstmt.setDouble(10, salt);
            pstmt.setDouble(11, calories);
            pstmt.setDouble(12, fat);
            pstmt.setDouble(13, sugar);
            JSONObject json2 = new JSONObject();
            json2.put("IngredientItems", new JSONArray(IngredientItems));
            String itemsStr = json1.toString();
            pstmt.setString(14, itemsStr);
            pstmt.setString(15, CountryOfOrigin);
            pstmt.setString(16, brand);
            pstmt.setDouble(17, protein);
            pstmt.setString(18, productType.toString());
            pstmt.setString(19, "Proteins");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getBrand() {
        return brand;
    }

    public double getProtein() {
        return protein;
    }

    //Override

    public ProteinProductType getProductType() {
        return productType;
    }

    @Override
    public String toString() {
        return "Proteins{" +
                "brand='" + brand + '\'' +
                ", protein=" + protein +
                ", productType=" + productType +
                "} " + super.toString();
    }
}
