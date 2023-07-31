package Categories.Home;

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

public class TV extends Home {
    private final int refreshRate;
    private final boolean mountableOnWall;
    private final boolean has3D;
    private final boolean hasStand;

    //Constructor

    public TV(String name, String color, int quantity, double price, UUID sellerID, boolean hasController, double height, double width, double weight, int refreshRate, boolean mountableOnWall, boolean has3D, boolean hasStand) {
        super(name, color, quantity, price, sellerID, hasController, height, width, weight);
        this.refreshRate = refreshRate;
        this.mountableOnWall = mountableOnWall;
        this.has3D = has3D;
        this.hasStand = hasStand;
        insert();
    }

    public TV(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasController, double height, double width, double weight, int refreshRate, boolean mountableOnWall, boolean has3D, boolean hasStand) {
        super(comments, id, name, color, price, sellerId, quantity, hasController, height, width, weight);
        this.refreshRate = refreshRate;
        this.mountableOnWall = mountableOnWall;
        this.has3D = has3D;
        this.hasStand = hasStand;
    }

    //Getters and Setters

    public int getRefreshRate() {
        return refreshRate;
    }

    public boolean isMountableOnWall() {
        return mountableOnWall;
    }

    public boolean isHas3D() {
        return has3D;
    }

    public boolean isHasStand() {
        return hasStand;
    }

    @Override
    public String toString() {
        return "TV{" +
                "refreshRate=" + refreshRate +
                ", mountableOnWall=" + mountableOnWall +
                ", has3D=" + has3D +
                ", hasStand=" + hasStand +
                "} " + super.toString();
    }

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, refreshRate, mountableOnWall, has3D, hasStand, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(isHasController()));
            pstmt.setDouble(9, getHeight());
            pstmt.setDouble(10, getWidth());
            pstmt.setDouble(11, getWeight());
            pstmt.setInt(12, refreshRate);
            pstmt.setString(13, Boolean.toString(mountableOnWall));
            pstmt.setString(14, Boolean.toString(has3D));
            pstmt.setString(15, Boolean.toString(hasStand));
            pstmt.setString(16, "TV");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadTVFromDatabase(ResultSet rs, Shop shop){
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
            boolean hasController = Boolean.parseBoolean(rs.getString("hasController"));
            double height = rs.getDouble("height");
            double width = rs.getDouble("width");
            double weight = rs.getDouble("weight");
            int refreshRate = rs.getInt("refreshRate");
            boolean mountableOnWall = Boolean.parseBoolean(rs.getString("mountableOnWall"));
            boolean has3D = Boolean.parseBoolean(rs.getString("has3D"));
            boolean hasStand = Boolean.parseBoolean(rs.getString("hasStand"));
            TV newTV = new TV(comments, productID, name, color, price, sellerID, quantity, hasController, height, width, weight, refreshRate, mountableOnWall, has3D, hasStand);
            shop.addProductToShopOnly(newTV);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
