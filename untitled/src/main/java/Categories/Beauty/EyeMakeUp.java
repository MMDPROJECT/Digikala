package Categories.Beauty;

import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static Connection.Connect.connect;

public class EyeMakeUp extends Beauty {
    private final PenType penType;
    private final boolean hasWaterResistance;
    private final String brand;
    private final int longevity;

    //Constructor

    public EyeMakeUp(String name, String color, int quantity, double price, UUID sellerID, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(name, color, quantity, price, sellerID, materialState, hasBox);
        this.penType = penType;
        this.hasWaterResistance = hasWaterResistance;
        this.brand = brand;
        this.longevity = longevity;
        insert();
    }

    public EyeMakeUp(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(comments, id, name, color, price, sellerId, quantity, materialState, hasBox);
        this.penType = penType;
        this.hasWaterResistance = hasWaterResistance;
        this.brand = brand;
        this.longevity = longevity;
    }

    //Getters and Setters

    public PenType getPenType() {
        return penType;
    }

    public boolean isHasWaterResistance() {
        return hasWaterResistance;
    }

    public String getBrand() {
        return brand;
    }

    public int getLongevity() {
        return longevity;
    }

    @Override
    public String toString() {
        return "EyeMakeUp{" +
                "penType=" + penType +
                ", hasWaterResistance=" + hasWaterResistance +
                ", brand='" + brand + '\'' +
                ", longevity=" + longevity +
                "} " + super.toString();
    }

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, MatterState, hasBox, penType, hasWaterResistance, brand, longevity, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, super.getProductID().toString());
            pstmt.setString(2, super.getName());
            pstmt.setString(3, super.getColor());
            pstmt.setDouble(4, super.getPrice());
            pstmt.setString(5, super.getSellerId().toString());
            pstmt.setInt(6, super.getQuantity());
            JSONObject json = new JSONObject();
            json.put("comments", new JSONArray(super.getComments()));
            String jsonComments = json.toString();
            pstmt.setString(7, jsonComments);
            pstmt.setString(8, getMaterialState().toString());
            pstmt.setString(9, Boolean.toString(isHasBox()));
            pstmt.setString(10, getPenType().toString());
            pstmt.setString(11, Boolean.toString(isHasWaterResistance()));
            pstmt.setString(12, getBrand());
            pstmt.setInt(13, getLongevity());
            pstmt.setString(14, "EyeMakeUp");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadEyeMakeUpFromDatabase(ResultSet rs, Shop shop){
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
            MatterState materialState = MatterState.valueOf(rs.getString("MatterState"));
            boolean hasBox = Boolean.parseBoolean(rs.getString("hasBox"));
            PenType penType = PenType.valueOf(rs.getString("penType"));
            boolean hasWaterResistance = Boolean.parseBoolean(rs.getString("hasWaterResistance"));
            String brand = rs.getString("brand");
            int longevity = rs.getInt("longevity");
            EyeMakeUp newEyeMakeUp = new EyeMakeUp(comments, productID, name, color, price, sellerID, quantity, materialState, hasBox, penType, hasWaterResistance, brand, longevity);
            shop.addProductToShopOnly(newEyeMakeUp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}