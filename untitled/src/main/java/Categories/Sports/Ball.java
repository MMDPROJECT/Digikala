package Categories.Sports;

import Categories.Sports.Enums.BallMaterial;
import Categories.Sports.Enums.BallSize;
import Connection.Connect;
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
        insert();
    }

    public Ball(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, BallSize size, BallMaterial material, boolean isRightHandOriented) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.size = size;
        this.material = material;
        this.isRightHandOriented = isRightHandOriented;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, size, material, isRightHandOriented, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
