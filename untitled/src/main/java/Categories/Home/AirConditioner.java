package Categories.Home;

import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AirConditioner extends Home {
    private final double coolingCapacity;
    private final double energyEfficiency;
    private final String airFilter;
    private final int fanNumber;
    private final boolean hasRemoteControl;
    private final boolean hasTimer;

    //Constructor

    public AirConditioner(String name, String color, int quantity, double price, UUID sellerID, boolean hasController, double height, double width, double weight, double coolingCapacity, double energyEfficiency, String airFilter, int fanNumber, boolean hasRemoteControl, boolean hasTimer) {
        super(name, color, quantity, price, sellerID, hasController, height, width, weight);
        this.coolingCapacity = coolingCapacity;
        this.energyEfficiency = energyEfficiency;
        this.airFilter = airFilter;
        this.fanNumber = fanNumber;
        this.hasRemoteControl = hasRemoteControl;
        this.hasTimer = hasTimer;
        insert();
    }

    public AirConditioner(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasController, double height, double width, double weight, double coolingCapacity, double energyEfficiency, String airFilter, int fanNumber, boolean hasRemoteControl, boolean hasTimer) {
        super(comments, id, name, color, price, sellerId, quantity, hasController, height, width, weight);
        this.coolingCapacity = coolingCapacity;
        this.energyEfficiency = energyEfficiency;
        this.airFilter = airFilter;
        this.fanNumber = fanNumber;
        this.hasRemoteControl = hasRemoteControl;
        this.hasTimer = hasTimer;
    }

    //Getters and Setters

    public double getCoolingCapacity() {
        return coolingCapacity;
    }

    public double getEnergyEfficiency() {
        return energyEfficiency;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public int getFanNumber() {
        return fanNumber;
    }

    //Override

    public boolean isHasRemoteControl() {
        return hasRemoteControl;
    }

    public boolean isHasTimer() {
        return hasTimer;
    }

    @Override
    public String toString() {
        return "AirConditioner{" +
                "coolingCapacity=" + coolingCapacity +
                ", energyEfficiency=" + energyEfficiency +
                ", airFilter='" + airFilter + '\'' +
                ", fanNumber=" + fanNumber +
                ", hasRemoteControl=" + hasRemoteControl +
                ", hasTimer=" + hasTimer +
                "} " + super.toString();
    }

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, coolingCapacity, energyEfficiency, airFilter, fanNumber, hasRemoteControl, hasTimer, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setDouble(12, coolingCapacity);
            pstmt.setDouble(13, energyEfficiency);
            pstmt.setString(14, airFilter);
            pstmt.setInt(15, fanNumber);
            pstmt.setString(16, Boolean.toString(hasRemoteControl));
            pstmt.setString(17, Boolean.toString(hasTimer));
            pstmt.setString(18, "AirConditioner");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
