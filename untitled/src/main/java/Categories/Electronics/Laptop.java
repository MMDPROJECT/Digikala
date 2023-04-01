package Categories.Electronics;

import Database_Insert.Connect;
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
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, String brand, String model, String OS, String screenSize, double batteryCapacity, String webcamModel, String CPU, String GPU, int fanNumber, boolean hasKeyboardLight, boolean hasFingerPrint, String keyboardLanguage, int portNumber) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, brand, model, OS, screenSize, batteryCapacity, webcamModel, CPU, GPU, fanNumber, hasKeyboardLight, hasFingerPrint, keyboardLanguage, portNumber) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(13, webcamModel);
            pstmt.setString(14, CPU);
            pstmt.setString(15, GPU);
            pstmt.setInt(16, fanNumber);
            pstmt.setString(17, Boolean.toString(hasKeyboardLight));
            pstmt.setString(18, Boolean.toString(hasFingerPrint));
            pstmt.setString(19, keyboardLanguage);
            pstmt.setInt(20, portNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public String getKeyboardLanguage() {
        return keyboardLanguage;
    }

    //Override

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
}
