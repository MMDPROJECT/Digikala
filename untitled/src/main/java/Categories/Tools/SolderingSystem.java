package Categories.Tools;

import Categories.Tools.Enums.PowerSource;
import Categories.Tools.Enums.UsageLevel;
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
        insert();
    }

    public SolderingSystem(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, UsageLevel usageLevel) {
        super(comments, id, name, color, price, sellerId, quantity, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.usageLevel = usageLevel;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, hasBox, isSilent, isChargeable, brand, voltage, powerSource, usageLevel, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(15, usageLevel.toString());
            pstmt.setString(16, "SolderingSystem");
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

    //Override

    public UsageLevel getUsageLevel() {
        return usageLevel;
    }

    @Override
    public String toString() {
        return "SolderingSystem{" +
                "voltage=" + voltage +
                ", powerSource=" + powerSource +
                ", usageLevel=" + usageLevel +
                "} " + super.toString();
    }

    public static void loadDrillFromDatabase(ResultSet rs, Shop shop){
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
            boolean hasBox = Boolean.parseBoolean(rs.getString("hasBox"));
            boolean isSilent = Boolean.parseBoolean(rs.getString("isSilent"));
            boolean isChargeable = Boolean.parseBoolean(rs.getString("isChargeable"));
            String brand = rs.getString("brand");
            int voltage = rs.getInt("voltage");
            PowerSource powerSource = PowerSource.valueOf(rs.getString("powerSource").toUpperCase());
            UsageLevel usageLevel = UsageLevel.valueOf(rs.getString("usageLevel").toUpperCase());
            SolderingSystem newSolderingSystem = new SolderingSystem(comments, productID, name, color, price, sellerID, quantity, weight, hasBox, isSilent, isChargeable, brand, voltage, powerSource, usageLevel);
            shop.addProductToShopOnly(newSolderingSystem);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
