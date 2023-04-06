package Categories.Vehicles;

import Categories.Vehicles.Enums.NoiseLevel;
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
        insert();
    }

    public Motorcycle(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, int seatNumber, boolean hasWingMirror, NoiseLevel noiseLevel) {
        super(comments, id, name, color, price, sellerId, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
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

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, seatNumber, hasWingMirror, noiseLevel, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(16, seatNumber);
            pstmt.setString(17, Boolean.toString(hasWingMirror));
            pstmt.setString(18, noiseLevel.toString());
            pstmt.setString(19, "Motorcycle");
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
            int seatNumber = rs.getInt("seatNumber");
            boolean hasWingMirror = Boolean.parseBoolean(rs.getString("hasWingMirror"));
            NoiseLevel noiseLevel = NoiseLevel.valueOf(rs.getString("noiseLevel").toUpperCase());
            Motorcycle newMotorcycle = new Motorcycle(comments, productID, name, color, price, sellerID, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, seatNumber, hasWingMirror, noiseLevel);
            shop.addProductToShopOnly(newMotorcycle);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
