package Categories.Vehicles;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Car extends Vehicles {
    private final boolean isRightSteering;
    private final String speakerModel;
    private final int seatNumber;

    //Constructor

    public Car(String name, String color, int quantity, double price, UUID sellerID, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        super(name, color, quantity, price, sellerID, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.isRightSteering = isRightSteering;
        this.speakerModel = speakerModel;
        this.seatNumber = seatNumber;
    }

    public Car(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        super(comments, id, name, color, price, sellerId, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.isRightSteering = isRightSteering;
        this.speakerModel = speakerModel;
        this.seatNumber = seatNumber;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, isRightSteering, speakerModel, seatNumber, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(16, Boolean.toString(isRightSteering));
            pstmt.setString(17, speakerModel);
            pstmt.setInt(18, seatNumber);
            pstmt.setString(19, "Car");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isRightSteering() {
        return isRightSteering;
    }

    public String getSpeakerModel() {
        return speakerModel;
    }

    //Override

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Car{" +
                "isRightSteering=" + isRightSteering +
                ", speakerModel='" + speakerModel + '\'' +
                ", seatNumber=" + seatNumber +
                "} " + super.toString();
    }
}
