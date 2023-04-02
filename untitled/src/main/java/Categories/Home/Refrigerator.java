package Categories.Home;

import Categories.Home.Enums.RefrigeratorType;
import Connection.Connect;
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
        insert();
    }

    public Refrigerator(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasController, double height, double width, double weight, int floorNumber, boolean hasFridge, RefrigeratorType refrigeratorType, boolean hasDigitalControllingSystem) {
        super(comments, id, name, color, price, sellerId, quantity, hasController, height, width, weight);
        this.floorNumber = floorNumber;
        this.hasFridge = hasFridge;
        this.refrigeratorType = refrigeratorType;
        this.hasDigitalControllingSystem = hasDigitalControllingSystem;
    }

    //Getters and Setters

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

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, floorNumber, hasFridge, refrigeratorType, hasDigitalControllingSystem, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(isHasController()));
            pstmt.setDouble(9, getHeight());
            pstmt.setDouble(10, getWidth());
            pstmt.setDouble(11, getWeight());
            pstmt.setInt(12, floorNumber);
            pstmt.setString(13, Boolean.toString(hasFridge));
            pstmt.setString(14, refrigeratorType.toString());
            pstmt.setString(15, Boolean.toString(hasDigitalControllingSystem));
            pstmt.setString(16, "Refrigerator");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
