package Categories.Tools;

import Categories.Tools.Enums.PowerSource;
import Categories.Tools.Enums.UsageLevel;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SolderingSystem extends Tools {
    private final int voltage;
    private final PowerSource powerSource;
    private final UsageLevel usageLevel;

    //Constructor

    public SolderingSystem(String name, String color, int quantity, double price, UUID sellerID, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, UsageLevel usageLevel) {
        super(name, color, quantity, price, sellerID, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.usageLevel = usageLevel;
    }


    //Getters and Setters

    public int getVoltage() {
        return voltage;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public UsageLevel getUsageLevel() {
        return usageLevel;
    }

    //Override

    @Override
    public String toString() {
        return "SolderingSystem{" +
                "voltage=" + voltage +
                ", powerSource=" + powerSource +
                ", usageLevel=" + usageLevel +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, UsageLevel usageLevel) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, hasBox, isSilent, isChargeable, brand, voltage, powerSource, usageLevel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(9, Boolean.toString(hasBox));
            pstmt.setString(10, Boolean.toString(isSilent));
            pstmt.setString(11, Boolean.toString(isChargeable));
            pstmt.setString(12, brand);
            pstmt.setInt(13, voltage);
            pstmt.setString(14, powerSource.toString());
            pstmt.setString(15, usageLevel.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
