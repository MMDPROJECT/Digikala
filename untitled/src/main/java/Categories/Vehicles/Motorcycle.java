package Categories.Vehicles;

import Categories.Vehicles.Enums.NoiseLevel;
import Database_Insert.Connect;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Motorcycle extends Vehicles {
    private final int seatNumber;
    private final boolean hasWingMirror;
    private final NoiseLevel noiseLevel;

    //Constructor

    public Motorcycle(String name, String color, int quantity, double price, UUID sellerID, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, int seatNumber, boolean hasWingMirror, NoiseLevel noiseLevel) {
        super(name, color, quantity, price, sellerID, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.seatNumber = seatNumber;
        this.hasWingMirror = hasWingMirror;
        this.noiseLevel = noiseLevel;
    }


    //Getters and Setters

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isHasWingMirror() {
        return hasWingMirror;
    }

    public NoiseLevel getNoiseLevel() {
        return noiseLevel;
    }

    //Override

    @Override
    public String toString() {
        return "Motorcycle{" +
                "seatNumber=" + seatNumber +
                ", hasWingMirror=" + hasWingMirror +
                ", noiseLevel=" + noiseLevel +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, int seatNumber, boolean hasWingMirror, NoiseLevel noiseLevel) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, seatNumber, hasWingMirror, noiseLevel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(16, seatNumber);
            pstmt.setString(17, Boolean.toString(hasWingMirror));
            pstmt.setString(18, noiseLevel.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
