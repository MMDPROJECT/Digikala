package Categories.Sports;

import Categories.Sports.Enums.BallMaterial;
import Categories.Sports.Enums.BallSize;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Ball extends Sports {
    private final BallSize size;
    private final BallMaterial material;
    private final boolean isRightHandOriented;

    //Constructor

    public Ball(String name, String color, int quantity, double price, UUID sellerID, double weight, String sportType, String brand, BallSize size, BallMaterial material, boolean isRightHandOriented) {
        super(name, color, quantity, price, sellerID, weight, sportType, brand);
        this.size = size;
        this.material = material;
        this.isRightHandOriented = isRightHandOriented;
    }

    public Ball(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, BallSize size, BallMaterial material, boolean isRightHandOriented) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.size = size;
        this.material = material;
        this.isRightHandOriented = isRightHandOriented;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, String sportType, String brand, BallSize size, BallMaterial material, boolean isRightHandOriented) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, size, material, isRightHandOriented, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(11, size.toString());
            pstmt.setString(12, material.toString());
            pstmt.setString(13, Boolean.toString(isRightHandOriented));
            pstmt.setString(14, "Ball");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public BallSize getSize() {
        return size;
    }

    public BallMaterial getMaterial() {
        return material;
    }

    public boolean isRightHandOriented() {
        return isRightHandOriented;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "size=" + size +
                ", material=" + material +
                ", isRightHandOriented=" + isRightHandOriented +
                "} " + super.toString();
    }
}
