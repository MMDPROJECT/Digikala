package Categories.Tools;

import Categories.Tools.Enums.PowerSource;
import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Drill extends Tools {
    private final int voltage;
    private final PowerSource powerSource;
    private final int minSpinSpeed;
    private final int maxSpinSpeed;

    //Constructor

    public Drill(String name, String color, int quantity, double price, UUID sellerID, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, int minSpinSpeed, int maxSpinSpeed) {
        super(name, color, quantity, price, sellerID, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.minSpinSpeed = minSpinSpeed;
        this.maxSpinSpeed = maxSpinSpeed;
        insert();
    }

    public Drill(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, int minSpinSpeed, int maxSpinSpeed) {
        super(comments, id, name, color, price, sellerId, quantity, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.minSpinSpeed = minSpinSpeed;
        this.maxSpinSpeed = maxSpinSpeed;
    }

    //Getters ana Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, hasBox, isSilent, isChargeable, brand, voltage, powerSource, minSpinSpeed, maxSpinSpeed, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(9, Boolean.toString(isHasBox()));
            pstmt.setString(10, Boolean.toString(isSilent()));
            pstmt.setString(11, Boolean.toString(isChargeable()));
            pstmt.setString(12, getBrand());
            pstmt.setInt(13, voltage);
            pstmt.setString(14, powerSource.toString());
            pstmt.setInt(15, minSpinSpeed);
            pstmt.setInt(16, maxSpinSpeed);
            pstmt.setString(17, "Drill");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getVoltage() {
        return voltage;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public int getMinSpinSpeed() {
        return minSpinSpeed;
    }

    //Override

    public int getMaxSpinSpeed() {
        return maxSpinSpeed;
    }

    @Override
    public String toString() {
        return "Drill{" +
                "voltage=" + voltage +
                ", powerSource=" + powerSource +
                ", minSpinSpeed=" + minSpinSpeed +
                ", maxSpinSpeed=" + maxSpinSpeed +
                "} " + super.toString();
    }
}
