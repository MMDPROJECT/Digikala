package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Sweater extends Clothes {
    private final int buttonNumber;
    private final String design;

    //Constructor

    public Sweater(String name, String color, int quantity, double price, UUID sellerID, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, String design) {
        super(name, color, quantity, price, sellerID, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.design = design;
    }

    public Sweater(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, String design) {
        super(comments, id, name, color, price, sellerId, quantity, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.design = design;
    }

    //Getter and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, String design) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ClothSize, ClothGender, ClothMaterial, brand, ClothDurability, buttonNumber, design, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, size.toString());
            pstmt.setString(9, gender.toString());
            pstmt.setString(10, material.toString());
            pstmt.setString(11, brand);
            pstmt.setString(12, durability.toString());
            pstmt.setInt(13, buttonNumber);
            pstmt.setString(14, design);
            pstmt.setString(15, "Sweater");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Override

    public int getButtonNumber() {
        return buttonNumber;
    }

    public String getDesign() {
        return design;
    }

    @Override
    public String toString() {
        return "Sweater{" +
                "buttonNumber=" + buttonNumber +
                ", design='" + design + '\'' +
                "} " + super.toString();
    }
}
