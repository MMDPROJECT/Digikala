package Categories.Electronics;

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

public class SmartWatch extends Electronics {
    private final String processor;
    private final boolean hasHeartRateTracker;
    private final boolean hasStepTracker;
    private final boolean hasCaloricTracker;

    //Constructor

    public SmartWatch(String name, String color, int quantity, double price, UUID sellerID, String brand, String model, String OS, String screenSize, double batteryCapacity, String processor, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        super(name, color, quantity, price, sellerID, brand, model, OS, screenSize, batteryCapacity);
        this.processor = processor;
        this.hasHeartRateTracker = hasHeartRateTracker;
        this.hasStepTracker = hasStepTracker;
        this.hasCaloricTracker = hasCaloricTracker;
        insert();
    }

    public SmartWatch(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String brand, String model, String OS, String screenSize, double batteryCapacity, String processor, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        super(comments, id, name, color, price, sellerId, quantity, brand, model, OS, screenSize, batteryCapacity);
        this.processor = processor;
        this.hasHeartRateTracker = hasHeartRateTracker;
        this.hasStepTracker = hasStepTracker;
        this.hasCaloricTracker = hasCaloricTracker;
    }

    //Getters and Setters

    public String getProcessor() {
        return processor;
    }

    public boolean isHasHeartRateTracker() {
        return hasHeartRateTracker;
    }

    //Override

    public boolean isHasStepTracker() {
        return hasStepTracker;
    }

    public boolean isHasCaloricTracker() {
        return hasCaloricTracker;
    }

    @Override
    public String toString() {
        return "SmartWatch{" +
                "processor='" + processor + '\'' +
                ", hasHeartRateTracker=" + hasHeartRateTracker +
                ", hasStepTracker=" + hasStepTracker +
                ", hasCaloricTracker=" + hasCaloricTracker +
                "} " + super.toString();
    }

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, brand, model, OS, screenSize, batteryCapacity, hasHeartRateTracker, hasStepTracker, hasCaloricTracker, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, getBrand());
            pstmt.setString(9, getModel());
            pstmt.setString(10, getOS());
            pstmt.setString(11, getScreenSize());
            pstmt.setDouble(12, getBatteryCapacity());
            pstmt.setString(13, Boolean.toString(hasHeartRateTracker));
            pstmt.setString(14, Boolean.toString(hasStepTracker));
            pstmt.setString(15, Boolean.toString(hasCaloricTracker));
            pstmt.setString(16, "SmartWatch");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadSmartWatchFromDatabase(ResultSet rs, Shop shop){
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
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            String OS = rs.getString("OS");
            String screenSize = rs.getString("screenSize");
            double batteryCapacity = rs.getDouble("batteryCapacity");
            String processor = rs.getString("processor");
            boolean hasHeartRateTracker = Boolean.parseBoolean(rs.getString("hasHeartRateTracker"));
            boolean hasStepTracker = Boolean.parseBoolean(rs.getString("hasStepTracker"));
            boolean hasCaloricTracker = Boolean.parseBoolean(rs.getString("hasCaloricTracker"));
            SmartWatch newSmartWatch = new SmartWatch(comments, productID, name, color, price, sellerID, quantity, brand, model, OS, screenSize, batteryCapacity, processor, hasHeartRateTracker, hasStepTracker, hasCaloricTracker);
            shop.addProductToShopOnly(newSmartWatch);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
