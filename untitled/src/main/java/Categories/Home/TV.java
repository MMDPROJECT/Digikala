package Categories.Home;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }


    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasController, double height, double width, double weight, int refreshRate, boolean mountableOnWall, boolean has3D, boolean hasStand) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasController, height, width, weight, refreshRate, mountableOnWall, has3D, hasStand) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(hasController));
            pstmt.setDouble(9, height);
            pstmt.setDouble(10, width);
            pstmt.setDouble(11, weight);
            pstmt.setInt(12, refreshRate);
            pstmt.setString(13, Boolean.toString(mountableOnWall));
            pstmt.setString(14, Boolean.toString(has3D));
            pstmt.setString(15, Boolean.toString(hasStand));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public boolean isMountableOnWall() {
        return mountableOnWall;
    }

    public boolean isHas3D() {
        return has3D;
    }

    //Override

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
}
