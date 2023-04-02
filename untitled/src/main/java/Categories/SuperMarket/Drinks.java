package Categories.SuperMarket;

import Categories.SuperMarket.Enums.DrinkSize;
import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Drinks extends SuperMarket {
    private final String taste;
    private final boolean isSoftDrink;
    private final double litters;
    private final DrinkSize size;

    //Constructor

    public Drinks(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String taste, boolean isSoftDrink, double litters, DrinkSize size) {
        super(name, color, quantity, price, sellerID, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.taste = taste;
        this.isSoftDrink = isSoftDrink;
        this.litters = litters;
        this.size = size;
        insert();
    }

    public Drinks(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String taste, boolean isSoftDrink, double litters, DrinkSize size) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.taste = taste;
        this.isSoftDrink = isSoftDrink;
        this.litters = litters;
        this.size = size;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, isSoftDrink, litters, size, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(16, Boolean.toString(isSoftDrink));
            pstmt.setDouble(17, litters);
            pstmt.setString(18, size.toString());
            pstmt.setString(19, "Drinks");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTaste() {
        return taste;
    }

    public boolean isSoftDrink() {
        return isSoftDrink;
    }

    public double getLitters() {
        return litters;
    }

    //Override

    public DrinkSize getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Drinks{" +
                "taste='" + taste + '\'' +
                ", isSoftDrink=" + isSoftDrink +
                ", litters=" + litters +
                ", size=" + size +
                "} " + super.toString();
    }
}
