package Categories.SuperMarket;

import Categories.SuperMarket.Enums.DairyGroups;
import Categories.SuperMarket.Enums.DrinkSize;
import Database_Insert.Connect;
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
    }


    //Getters and Setters

    public String getTaste() {
        return taste;
    }

    public boolean isSoftDrink() {
        return isSoftDrink;
    }

    public double getLitters() {
        return litters;
    }

    public DrinkSize getSize() {
        return size;
    }

    //Override

    @Override
    public String toString() {
        return "Drinks{" +
                "taste='" + taste + '\'' +
                ", isSoftDrink=" + isSoftDrink +
                ", litters=" + litters +
                ", size=" + size +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> IngredientItems, String CountryOfOrigin, boolean isSoftDrink, double litters, DrinkSize size) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, isSoftDrink, litters, size) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(16, Boolean.toString(isSoftDrink));
            pstmt.setDouble(17, litters);
            pstmt.setString(18, size.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
