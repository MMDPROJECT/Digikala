package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Coat extends Clothes {
    private final int buttonNumber;
    private final boolean hasCap;

    //Constructor

    public Coat(String name, String color, int quantity, double price, UUID sellerID, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, boolean hasCap) {
        super(name, color, quantity, price, sellerID, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.hasCap = hasCap;
        insert();
    }

    public Coat(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, boolean hasCap) {
        super(comments, id, name, color, price, sellerId, quantity, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.hasCap = hasCap;
    }

    //Getter and Setters

    public int getButtonNumber() {
        return buttonNumber;
    }

    public boolean isHasCap() {
        return hasCap;
    }

    @Override
    public String toString() {
        return "Coat{" +
                "buttonNumber=" + buttonNumber +
                ", hasCap=" + hasCap +
                "} " + super.toString();
    }

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ClothSize, ClothGender, ClothMaterial, brand, ClothDurability, buttonNumber, hasCap, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, getSize().toString());
            pstmt.setString(9, getGender().toString());
            pstmt.setString(10, getMaterial().toString());
            pstmt.setString(11, getBrand());
            pstmt.setString(12, getDurability().toString());
            pstmt.setInt(13, buttonNumber);
            pstmt.setString(14, Boolean.toString(hasCap));
            pstmt.setString(15, "Coat");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
