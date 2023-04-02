package Categories.Sports;

import Categories.Sports.Enums.RacketDurability;
import Database_Insert.Connect;
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
    }

    public Rackets(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String shape) {
        super(comments, id, name, color, price, sellerId, quantity, weight, sportType, brand);
        this.length = length;
        this.width = width;
        this.durability = durability;
        Shape = shape;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String Shape) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, sportType, brand, length, width, durability, Shape, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
}
