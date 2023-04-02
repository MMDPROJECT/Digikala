package Categories.Home;

import Database_Insert.Connect;
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

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasController, double height, double width, double weight, double coolingCapacity, double energyEfficiency, String airFilter, int fanNumber, boolean hasRemoteControl, boolean hasTimer) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, coolingCapacity, energyEfficiency, airFilter, fanNumber, hasRemoteControl, hasTimer, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
}
