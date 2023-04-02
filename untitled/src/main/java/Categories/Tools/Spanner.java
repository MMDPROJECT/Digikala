package Categories.Tools;

import Categories.Tools.Enums.SpannerMaterial;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }

    public Spanner(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        super(comments, id, name, color, price, sellerId, quantity, weight, hasBox, isSilent, isChargeable, brand);
        this.size = size;
        this.style = style;
        this.material = material;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, weight, hasBox, isSilent, isChargeable, brand, size, style, material, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(13, size);
            pstmt.setString(14, style);
            pstmt.setString(15, material.toString());
            pstmt.setString(16, "Spanner");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

    @Override
    public String toString() {
        return "Spanner{" +
                "size=" + size +
                ", style='" + style + '\'' +
                ", material=" + material +
                "} " + super.toString();
    }
}
