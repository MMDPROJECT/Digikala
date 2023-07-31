package Categories.Vehicles;

import Connection.Connect;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        insert();
    }

    public Car(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        super(comments, id, name, color, price, sellerId, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.isRightSteering = isRightSteering;
        this.speakerModel = speakerModel;
        this.seatNumber = seatNumber;
    }

    //Getters and Setters

    public boolean isRightSteering() {
        return isRightSteering;
    }

    public String getSpeakerModel() {
        return speakerModel;
    }

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

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, isRightSteering, speakerModel, seatNumber, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(9, getHorsePower());
            pstmt.setString(10, getEngineModel());
            pstmt.setInt(11, getWheelNumber());
            pstmt.setString(12, Boolean.toString(isAutomatic()));
            pstmt.setInt(13, getMaxSpeed());
            pstmt.setString(14, getBrand());
            pstmt.setString(15, getEngineModel());
            pstmt.setString(16, Boolean.toString(isRightSteering));
            pstmt.setString(17, speakerModel);
            pstmt.setInt(18, seatNumber);
            pstmt.setString(19, "Car");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadCarFromDatabase(ResultSet rs, Shop shop){
        try {
            // loop through the result set
            UUID productID = UUID.fromString(rs.getString("ProductID"));
            UUID sellerID = UUID.fromString(rs.getString("sellerID"));
            String name = rs.getString("name");
            String color = rs.getString("color");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            JSONObject jsonComments = new JSONObject(rs.getString("comments"));
            JSONArray jsonArray = jsonComments.getJSONArray("comments");
            ArrayList<String> comments = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                comments.add(jsonArray.getString(i));
            }
            double weight = rs.getDouble("weight");
            int horsePower = rs.getInt("horsePower");
            String engineModel = rs.getString("engineModel");
            int wheelNumber = rs.getInt("wheelNumber");
            boolean isAutomatic = Boolean.parseBoolean(rs.getString("isAutomatic"));
            int maxSpeed = rs.getInt("maxSpeed");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            boolean isRightSteering = Boolean.parseBoolean(rs.getString("isRightSteering"));
            String speakerModel = rs.getString("speakerModel");
            int seatNumber = rs.getInt("seatNumber");
            Car newCar = new Car(comments, productID, name, color, price, sellerID, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, isRightSteering, speakerModel, seatNumber);
            shop.addProductToShopOnly(newCar);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
