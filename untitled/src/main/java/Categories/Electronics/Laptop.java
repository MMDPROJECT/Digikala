package Categories.Electronics;

import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Laptop extends Electronics {
    private final String webcamModel;
    private final String CPU;
    private final String GPU;
    private final int fanNumber;
    private final boolean hasKeyboardLight;
    private final boolean hasFingerPrint;
    private final String keyboardLanguage;
    private final int portNumber;

    //Constructor

    public Laptop(String name, String color, int quantity, double price, UUID sellerID, String brand, String model, String OS, String screenSize, double batteryCapacity, String webcamModel, String CPU, String GPU, int fanNumber, boolean hasKeyboardLight, boolean hasFingerPrint, String keyboardLanguage, int portNumber) {
        super(name, color, quantity, price, sellerID, brand, model, OS, screenSize, batteryCapacity);
        this.webcamModel = webcamModel;
        this.CPU = CPU;
        this.GPU = GPU;
        this.fanNumber = fanNumber;
        this.hasKeyboardLight = hasKeyboardLight;
        this.hasFingerPrint = hasFingerPrint;
        this.keyboardLanguage = keyboardLanguage;
        this.portNumber = portNumber;
        insert();
    }

    public Laptop(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String brand, String model, String OS, String screenSize, double batteryCapacity, String webcamModel, String CPU, String GPU, int fanNumber, boolean hasKeyboardLight, boolean hasFingerPrint, String keyboardLanguage, int portNumber) {
        super(comments, id, name, color, price, sellerId, quantity, brand, model, OS, screenSize, batteryCapacity);
        this.webcamModel = webcamModel;
        this.CPU = CPU;
        this.GPU = GPU;
        this.fanNumber = fanNumber;
        this.hasKeyboardLight = hasKeyboardLight;
        this.hasFingerPrint = hasFingerPrint;
        this.keyboardLanguage = keyboardLanguage;
        this.portNumber = portNumber;
    }

    //Getters and Setters

    public String getWebcamModel() {
        return webcamModel;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public int getFanNumber() {
        return fanNumber;
    }

    public boolean isHasKeyboardLight() {
        return hasKeyboardLight;
    }

    public boolean isHasFingerPrint() {
        return hasFingerPrint;
    }

    //Override

    public String getKeyboardLanguage() {
        return keyboardLanguage;
    }

    public int getPortNumber() {
        return portNumber;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "webcamModel='" + webcamModel + '\'' +
                ", CPU='" + CPU + '\'' +
                ", GPU='" + GPU + '\'' +
                ", fanNumber='" + fanNumber + '\'' +
                ", hasKeyboardLight=" + hasKeyboardLight +
                ", hasFingerPrint=" + hasFingerPrint +
                ", keyboardLanguage='" + keyboardLanguage + '\'' +
                ", portNumber=" + portNumber +
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
            pstmt.setString(13, webcamModel);
            pstmt.setString(14, CPU);
            pstmt.setString(15, GPU);
            pstmt.setInt(16, fanNumber);
            pstmt.setString(17, Boolean.toString(hasKeyboardLight));
            pstmt.setString(18, Boolean.toString(hasFingerPrint));
            pstmt.setString(19, keyboardLanguage);
            pstmt.setInt(20, portNumber);
            pstmt.setString(21, "SmartPhone");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
