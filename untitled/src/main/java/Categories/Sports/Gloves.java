package Categories.Sports;

import Categories.Sports.Enums.GloveMaterial;
import Categories.Sports.Enums.GloveSize;
import Categories.Sports.Enums.GloveStyle;
import Categories.Sports.Enums.GloveUser;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }

    public Gloves(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, GloveMaterial material, GloveSize size, GloveUser suggestedUser, GloveStyle style) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.material = material;
        this.size = size;
        this.suggestedUser = suggestedUser;
        this.style = style;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, String sportType, String brand, GloveMaterial material, GloveSize size, GloveUser suggestedUser, GloveStyle style) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, material, size, suggestedUser, style, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setDouble(8, weight);
            pstmt.setString(9, sportType);
            pstmt.setString(10, brand);
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
}
