package Categories.Sports;

import Categories.Sports.Enums.RacketDurability;
import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Rackets extends Sports {
    private final double length;
    private final double width;
    private final RacketDurability durability;
    private final String Shape;

    //Constructor

    public Rackets(String name, String color, int quantity, double price, UUID sellerID, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String shape) {
        super(name, color, quantity, price, sellerID, weight, sportType, brand);
        this.length = length;
        this.width = width;
        this.durability = durability;
        Shape = shape;
        insert();
    }

    public Rackets(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String shape) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.length = length;
        this.width = width;
        this.durability = durability;
        Shape = shape;
    }

    //Getters and Setters

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public RacketDurability getDurability() {
        return durability;
    }

    //Override

    public String getShape() {
        return Shape;
    }

    @Override
    public String toString() {
        return "Rackets{" +
                "length=" + length +
                ", width=" + width +
                ", durability=" + durability +
                ", Shape='" + Shape + '\'' +
                "} " + super.toString();
    }

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, length, width, durability, Shape, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setDouble(11, length);
            pstmt.setDouble(12, width);
            pstmt.setString(13, durability.toString());
            pstmt.setString(14, Shape);
            pstmt.setString(15, "Rackets");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
