package Categories.SuperMarket;

import Categories.SuperMarket.Enums.ProteinProductType;
import Connection.Connect;
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
        insert();
    }

    public Proteins(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String brand, double protein, ProteinProductType productType) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.brand = brand;
        this.protein = protein;
        this.productType = productType;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, brand, protein, productType, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = Connect.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, getProductID().toString());
            pstmt.setString(2, getName());
            pstmt.setString(3, getColor());
            pstmt.setDouble(4, getPrice());
            pstmt.setString(5, getSellerId().toString());
            pstmt.setInt(6, getQuantity());
            JSONObject jsonComments = new JSONObject();
            jsonComments.put("comments", new JSONArray(getComments()));
            String strComments = jsonComments.toString();
            pstmt.setString(7, strComments);
            pstmt.setString(8, Boolean.toString(isHasBox()));
            pstmt.setDouble(9, getWeight());
            pstmt.setDouble(10, getSalt());
            pstmt.setDouble(11, getCalories());
            pstmt.setDouble(12, getFat());
            pstmt.setDouble(13, getSugar());
            JSONObject jsonItems = new JSONObject();
            jsonItems.put("IngredientItems", new JSONArray(getIngredientItems()));
            String itemsStr = jsonItems.toString();
            pstmt.setString(14, itemsStr);
            pstmt.setString(15, getCountryOfOrigin());
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
