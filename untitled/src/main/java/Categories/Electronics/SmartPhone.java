package Categories.Electronics;

import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class SmartPhone extends Electronics {
    private final int rearCameraQuality;       //Quality of the main camera in megapixels
    private final int selfieCameraQuality;      //Quality of the selfie camera in megapixels
    private final int cameraNumber;
    private final int storage;
    private final double OSVersion;
    private final String displayResolution;
    private final String ringTone;
    private final String CPU;

    //Constructor

    public SmartPhone(String name, String color, int quantity, double price, UUID sellerID, String brand, String model, String OS, String screenSize, double batteryCapacity, int rearCameraQuality, int selfieCameraQuality, int cameraNumber, int storage, double OSVersion, String displayResolution, String ringTone, String CPU) {
        super(name, color, quantity, price, sellerID, brand, model, OS, screenSize, batteryCapacity);
        this.rearCameraQuality = rearCameraQuality;
        this.selfieCameraQuality = selfieCameraQuality;
        this.cameraNumber = cameraNumber;
        this.storage = storage;
        this.OSVersion = OSVersion;
        this.displayResolution = displayResolution;
        this.ringTone = ringTone;
        this.CPU = CPU;
        insert();
    }

    public SmartPhone(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String brand, String model, String OS, String screenSize, double batteryCapacity, int rearCameraQuality, int selfieCameraQuality, int cameraNumber, int storage, double OSVersion, String displayResolution, String ringTone, String CPU) {
        super(comments, id, name, color, price, sellerId, quantity, brand, model, OS, screenSize, batteryCapacity);
        this.rearCameraQuality = rearCameraQuality;
        this.selfieCameraQuality = selfieCameraQuality;
        this.cameraNumber = cameraNumber;
        this.storage = storage;
        this.OSVersion = OSVersion;
        this.displayResolution = displayResolution;
        this.ringTone = ringTone;
        this.CPU = CPU;
    }

    //Getters and Setters

    public int getRearCameraQuality() {
        return rearCameraQuality;
    }

    public int getSelfieCameraQuality() {
        return selfieCameraQuality;
    }

    public int getCameraNumber() {
        return cameraNumber;
    }

    public int getStorage() {
        return storage;
    }

    public double getOSVersion() {
        return OSVersion;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    //Override

    public String getRingTone() {
        return ringTone;
    }

    public String getCPU() {
        return CPU;
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "rearCameraQuality=" + rearCameraQuality +
                ", selfieCameraQuality=" + selfieCameraQuality +
                ", cameraNumber=" + cameraNumber +
                ", storage=" + storage +
                ", OSVersion=" + OSVersion +
                ", displayResolution='" + displayResolution + '\'' +
                ", ringTone='" + ringTone + '\'' +
                ", CPU='" + CPU + '\'' +
                "} " + super.toString();
    }

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, brand, model, OS, screenSize, batteryCapacity, hasHeartRateTracker, hasStepTracker, hasCaloricTracker, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(13, rearCameraQuality);
            pstmt.setInt(14, selfieCameraQuality);
            pstmt.setInt(15, cameraNumber);
            pstmt.setInt(16, storage);
            pstmt.setDouble(17, OSVersion);
            pstmt.setString(18, displayResolution);
            pstmt.setString(19, ringTone);
            pstmt.setString(20, CPU);
            pstmt.setString(21, "SmartPhone");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
