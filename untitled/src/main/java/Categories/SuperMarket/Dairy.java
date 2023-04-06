package Categories.SuperMarket;

import Categories.SuperMarket.Enums.DairyGroups;
import Connection.Connect;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Dairy extends SuperMarket {
    private final boolean isDomestic;
    private final DairyGroups dairyGroup;

    //Constructor

    public Dairy(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, boolean isDomestic, DairyGroups dairyGroup) {
        super(name, color, quantity, price, sellerID, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.isDomestic = isDomestic;
        this.dairyGroup = dairyGroup;
        insert();
    }

    public Dairy(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, boolean isDomestic, DairyGroups dairyGroup) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.isDomestic = isDomestic;
        this.dairyGroup = dairyGroup;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, isDomestic, dairyGroup, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(16, Boolean.toString(isDomestic));
            pstmt.setString(17, dairyGroup.toString());
            pstmt.setString(18, "Dairy");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    //Override

    public DairyGroups getDairyGroup() {
        return dairyGroup;
    }

    @Override
    public String toString() {
        return "Dairy{" +
                "isDomestic=" + isDomestic +
                ", dairyGroup=" + dairyGroup +
                "} " + super.toString();
    }

    public static void loadDairyFromDatabase(ResultSet rs, Shop shop){
        try {
            // loop through the result set
            UUID productID = UUID.fromString(rs.getString("ProductID"));
            UUID sellerID = UUID.fromString(rs.getString("sellerID"));
            String name = rs.getString("name");
            String color = rs.getString("color");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            JSONObject jsonComments = new JSONObject(rs.getString("comments"));
            JSONArray jsonArray = jsonComments.getJSONArray("comments");
            ArrayList<String> comments = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                comments.add(jsonArray.getString(i));
            }
            boolean hasBox = Boolean.parseBoolean(rs.getString("hasBox"));
            double weight = rs.getDouble("weight");
            double salt = rs.getDouble("salt");
            double calories = rs.getDouble("calories");
            double fat = rs.getDouble("fat");
            double sugar = rs.getDouble("sugar");
            JSONObject jsonIngredientItems = new JSONObject(rs.getString("IngredientItems"));
            JSONArray jsonArray2 = jsonComments.getJSONArray("IngredientItems");
            ArrayList<String> IngredientItems = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                IngredientItems.add(jsonArray.getString(i));
            }
            String CountryOfOrigin = rs.getString("CountryOfOrigin");
            boolean isDomestic = Boolean.parseBoolean(rs.getString("isDomestic"));
            DairyGroups dairyGroup = DairyGroups.valueOf(rs.getString("dairyGroup").toUpperCase());
            Dairy newDairy = new Dairy(comments, productID, name, color, price, sellerID, quantity, hasBox, weight, salt, calories, fat, sugar, IngredientItems, CountryOfOrigin, isDomestic, dairyGroup);
            shop.addProductToShopOnly(newDairy);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
