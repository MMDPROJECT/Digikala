package Categories.Tools;

import Categories.Tools.Enums.SpannerMaterial;
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

public class Spanner extends Tools {
    private final int size;
    private final String style;   //Example: Combination open end / 12 point / 15° / Offset ring end
    private final SpannerMaterial material;

    //Constructor

    public Spanner(String name, String color, int quantity, double price, UUID sellerID, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        super(name, color, quantity, price, sellerID, weight, hasBox, isSilent, isChargeable, brand);
        this.size = size;
        this.style = style;
        this.material = material;
        insert();
    }

    public Spanner(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        super(comments, id, name, color, price, sellerId, quantity, weight, hasBox, isSilent, isChargeable, brand);
        this.size = size;
        this.style = style;
        this.material = material;
    }

    //Getters and Setters

    public int getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }

    //Override

    public SpannerMaterial getMaterial() {
        return material;
    }

    //Override

    @Override
    public String toString() {
        return "Spanner{" +
                "size=" + size +
                ", style='" + style + '\'' +
                ", material=" + material +
                "} " + super.toString();
    }

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, hasBox, isSilent, isChargeable, brand, size, style, material, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(13, size);
            pstmt.setString(14, style);
            pstmt.setString(15, material.toString());
            pstmt.setString(16, "Spanner");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadSpannerFromDatabase(ResultSet rs, Shop shop){
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
            int size = rs.getInt("size");
            String style = rs.getString("style");   //Example: Combination open end / 12 point / 15° / Offset ring end
            SpannerMaterial material = SpannerMaterial.valueOf(rs.getString("material").toUpperCase());
            Spanner newSpanner = new Spanner(comments, productID, name, color, price, sellerID, quantity, weight, hasBox, isSilent, isChargeable, brand, size, style, material);
            shop.addProductToShopOnly(newSpanner);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
