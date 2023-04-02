package Categories.Electronics;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }

    public SmartWatch(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String brand, String model, String OS, String screenSize, double batteryCapacity, String processor, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        super(comments, id, name, color, price, sellerId, quantity, brand, model, OS, screenSize, batteryCapacity);
        this.processor = processor;
        this.hasHeartRateTracker = hasHeartRateTracker;
        this.hasStepTracker = hasStepTracker;
        this.hasCaloricTracker = hasCaloricTracker;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, String brand, String model, String OS, String screenSize, double batteryCapacity, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, brand, model, OS, screenSize, batteryCapacity, hasHeartRateTracker, hasStepTracker, hasCaloricTracker, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, brand);
            pstmt.setString(9, model);
            pstmt.setString(10, OS);
            pstmt.setString(11, screenSize);
            pstmt.setDouble(12, batteryCapacity);
            pstmt.setString(13, Boolean.toString(hasHeartRateTracker));
            pstmt.setString(14, Boolean.toString(hasStepTracker));
            pstmt.setString(15, Boolean.toString(hasCaloricTracker));
            pstmt.setString(16, "SmartWatch");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
}
