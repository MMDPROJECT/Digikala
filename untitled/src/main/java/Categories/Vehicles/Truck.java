package Categories.Vehicles;

import Categories.Vehicles.Enums.TruckType;
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

public class Truck extends Vehicles {
    private final TruckType truckType;
    private final boolean hasBed;

    //Constructor

    public Truck(String name, String color, int quantity, double price, UUID sellerID, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        super(name, color, quantity, price, sellerID, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.truckType = truckType;
        this.hasBed = hasBed;
        insert();
    }

    public Truck(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        super(comments, id, name, color, price, sellerId, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
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

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, truckType, hasBed, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(16, truckType.toString());
            pstmt.setString(17, Boolean.toString(hasBed));
            pstmt.setString(18, "Truck");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadTruckFromDatabase(ResultSet rs, Shop shop){
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
            TruckType truckType = TruckType.valueOf(rs.getString("truckType"));
            boolean hasBed = Boolean.parseBoolean(rs.getString("hasBed"));
            Truck newTruck = new Truck(comments, productID, name, color, price, sellerID, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model, truckType, hasBed);
            shop.addProductToShopOnly(newTruck);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
