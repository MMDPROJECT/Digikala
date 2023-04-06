package Categories.Sports;

import Categories.Sports.Enums.*;
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

public class Gloves extends Sports {
    private final GloveMaterial material;
    private final GloveSize size;
    private final GloveUser suggestedUser;
    private final GloveStyle style;

    //Constructor

    public Gloves(String name, String color, int quantity, double price, UUID sellerID, double weight, String sportType, String brand, GloveMaterial material, GloveSize size, GloveUser suggestedUser, GloveStyle style) {
        super(name, color, quantity, price, sellerID, weight, sportType, brand);
        this.material = material;
        this.size = size;
        this.suggestedUser = suggestedUser;
        this.style = style;
        insert();
    }

    public Gloves(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, GloveMaterial material, GloveSize size, GloveUser suggestedUser, GloveStyle style) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.material = material;
        this.size = size;
        this.suggestedUser = suggestedUser;
        this.style = style;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, material, size, suggestedUser, style, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setDouble(8, getWeight());
            pstmt.setString(9, getSportType());
            pstmt.setString(10, getBrand());
            pstmt.setString(11, material.toString());
            pstmt.setString(12, size.toString());
            pstmt.setString(13, suggestedUser.toString());
            pstmt.setString(14, style.toString());
            pstmt.setString(15, "Gloves");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public GloveMaterial getMaterial() {
        return material;
    }

    public GloveSize getSize() {
        return size;
    }

    public GloveUser getSuggestedUser() {
        return suggestedUser;
    }

    //Override

    public GloveStyle getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return "Gloves{" +
                "material=" + material +
                ", size=" + size +
                ", suggestedUser=" + suggestedUser +
                ", style=" + style +
                "} " + super.toString();
    }

    public static void loadGlovesFromDatabase(ResultSet rs, Shop shop){
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
            double weight = rs.getDouble("weight");
            String sportType = rs.getString("sportType");
            String brand = rs.getString("brand");
            GloveMaterial material = GloveMaterial.valueOf(rs.getString("material").toUpperCase());
            GloveSize size = GloveSize.valueOf(rs.getString("size").toUpperCase());
            GloveUser suggestedUser = GloveUser.valueOf(rs.getString("suggestedUser"));
            GloveStyle style = GloveStyle.valueOf(rs.getString("style"));
            Gloves newGloves = new Gloves(comments, productID, name, color, price, sellerID, quantity, weight, sportType, brand, material, size, suggestedUser, style);
            shop.addProductToShopOnly(newGloves);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
