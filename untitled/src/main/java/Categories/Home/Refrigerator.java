package Categories.Home;

import Categories.Home.Enums.RefrigeratorType;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Refrigerator extends Home {
    private final int floorNumber;
    private final boolean hasFridge;
    private final RefrigeratorType refrigeratorType;
    private final boolean hasDigitalControllingSystem;

    //Constructor

    public Refrigerator(String name, String color, int quantity, double price, UUID sellerID, boolean hasController, double height, double width, double weight, int floorNumber, boolean hasFridge, RefrigeratorType refrigeratorType, boolean hasDigitalControllingSystem) {
        super(name, color, quantity, price, sellerID, hasController, height, width, weight);
        this.floorNumber = floorNumber;
        this.hasFridge = hasFridge;
        this.refrigeratorType = refrigeratorType;
        this.hasDigitalControllingSystem = hasDigitalControllingSystem;
    }


    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasController, double height, double width, double weight, int floorNumber, boolean hasFridge, RefrigeratorType refrigeratorType, boolean hasDigitalControllingSystem) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, floorNumber, hasFridge, refrigeratorType, hasDigitalControllingSystem) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(hasController));
            pstmt.setDouble(9, height);
            pstmt.setDouble(10, width);
            pstmt.setDouble(11, weight);
            pstmt.setInt(12, floorNumber);
            pstmt.setString(13, Boolean.toString(hasFridge));
            pstmt.setString(14, refrigeratorType.toString());
            pstmt.setString(15, Boolean.toString(hasDigitalControllingSystem));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isHasFridge() {
        return hasFridge;
    }

    public RefrigeratorType getRefrigeratorType() {
        return refrigeratorType;
    }

    //Override

    public boolean isHasDigitalControllingSystem() {
        return hasDigitalControllingSystem;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "floorNumber=" + floorNumber +
                ", hasFridge=" + hasFridge +
                ", refrigeratorType=" + refrigeratorType +
                ", hasDigitalControllingSystem=" + hasDigitalControllingSystem +
                "} " + super.toString();
    }
}
