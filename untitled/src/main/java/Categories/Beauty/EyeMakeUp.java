package Categories.Beauty;

import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static Database_Insert.Connect.connect;

public class EyeMakeUp extends Beauty {
    private final PenType penType;
    private final boolean hasWaterResistance;
    private final String brand;
    private final int longevity;

    //Constructor

    public EyeMakeUp(String name, String color, int quantity, double price, UUID sellerID, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(name, color, quantity, price, sellerID, materialState, hasBox);
        this.penType = penType;
        this.hasWaterResistance = hasWaterResistance;
        this.brand = brand;
        this.longevity = longevity;
    }

    public EyeMakeUp(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(comments, id, name, color, price, sellerId, quantity, materialState, hasBox);
        this.penType = penType;
        this.hasWaterResistance = hasWaterResistance;
        this.brand = brand;
        this.longevity = longevity;
    }

    //Getters and Setters

    public static void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, MatterState, hasBox, penType, hasWaterResistance, brand, longevity, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, );
            pstmt.setString(2, name);
            pstmt.setString(3, color);
            pstmt.setDouble(4, price);
            pstmt.setString(5, sellerID.toString());
            pstmt.setInt(6, quantity);
            JSONObject json = new JSONObject();
            json.put("comments", new JSONArray(comments));
            String strComments = json.toString();
            pstmt.setString(7, strComments);
            pstmt.setString(8, materialState.toString());
            pstmt.setString(9, Boolean.toString(hasBox));
            pstmt.setString(10, penType.toString());
            pstmt.setString(11, Boolean.toString(hasWaterResistance));
            pstmt.setString(12, brand);
            pstmt.setInt(13, longevity);
            pstmt.setString(14, "EyeMakeUp");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public PenType getPenType() {
        return penType;
    }

    public boolean isHasWaterResistance() {
        return hasWaterResistance;
    }

    public String getBrand() {
        return brand;
    }

    //Override
    public int getLongevity() {
        return longevity;
    }

    @Override
    public String toString() {
        return "EyeMakeUp{" +
                "penType=" + penType +
                ", hasWaterResistance=" + hasWaterResistance +
                ", brand='" + brand + '\'' +
                ", longevity=" + longevity +
                "} " + super.toString();
    }
}