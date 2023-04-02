package Categories.Vehicles;

import Categories.Vehicles.Enums.NoiseLevel;
import Categories.Vehicles.Enums.TruckType;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Truck extends Vehicles {
    private final TruckType truckType;
    private final boolean hasBed;

    //Constructor

    public Truck(String name, String color, int quantity, double price, UUID sellerID, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        super(name, color, quantity, price, sellerID, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.truckType = truckType;
        this.hasBed = hasBed;
    }


    //Getters and Setters

    public TruckType getTruckType() {
        return truckType;
    }

    public boolean isHasBed() {
        return hasBed;
    }

    //Override

    @Override
    public String toString() {
        return "Truck{" +
                "truckType=" + truckType +
                ", hasBed=" + hasBed +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, truckType, hasBed) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(9, horsePower);
            pstmt.setString(10, engineModel);
            pstmt.setInt(11, wheelNumber);
            pstmt.setString(12, Boolean.toString(isAutomatic));
            pstmt.setInt(13, maxSpeed);
            pstmt.setString(14, brand);
            pstmt.setString(15, model);
            pstmt.setString(16, truckType.toString());
            pstmt.setString(17, Boolean.toString(hasBed));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
