package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
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

public class Jean extends Clothes {
    private final double height;
    private final int pocketNumber;
    private final boolean hasZipper;

    //Constructor

    public Jean(String name, String color, int quantity, double price, UUID sellerID, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, double height, int pocketNumber, boolean hasZipper) {
        super(name, color, quantity, price, sellerID, size, gender, material, brand, durability);
        this.height = height;
        this.pocketNumber = pocketNumber;
        this.hasZipper = hasZipper;
        insert();
    }

    public Jean(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, double height, int pocketNumber, boolean hasZipper) {
        super(comments, id, name, color, price, sellerId, quantity, size, gender, material, brand, durability);
        this.height = height;
        this.pocketNumber = pocketNumber;
        this.hasZipper = hasZipper;
    }

    //Getter and Setters

    public double getHeight() {
        return height;
    }

    public int getPocketNumber() {
        return pocketNumber;
    }

    public boolean isHasZipper() {
        return hasZipper;
    }

    @Override
    public String toString() {
        return "Jean{" +
                "height=" + height +
                ", pocketNumber=" + pocketNumber +
                ", hasZipper=" + hasZipper +
                "} " + super.toString();
    }

    //Database - Related methods
    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ClothSize, ClothGender, ClothMaterial, brand, ClothDurability, height, pocketNumber, hasZipper, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setDouble(13, height);
            pstmt.setInt(14, pocketNumber);
            pstmt.setString(15, Boolean.toString(hasZipper));
            pstmt.setString(16, "Jean");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadJeanFromDatabase(ResultSet rs, Shop shop){
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
            ClothSize size = ClothSize.valueOf(rs.getString("size").toUpperCase());
            ClothGender gender = ClothGender.valueOf(rs.getString("gender").toUpperCase());
            ClothMaterial material = ClothMaterial.valueOf(rs.getString("material").toUpperCase());
            String brand = rs.getString("brand");
            ClothDurability durability = ClothDurability.valueOf(rs.getString("durability").toUpperCase());
            double height = rs.getDouble("height");
            int pocketNumber = rs.getInt("pocketNumber");
            boolean hasZipper = Boolean.parseBoolean(rs.getString("hasZipper"));
            Jean newJean = new Jean(comments, productID, name, color, price, sellerID, quantity, size, gender, material, brand, durability, height, pocketNumber, hasZipper);
            shop.addProductToShopOnly(newJean);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
