package Shopping;

import Categories.Beauty.EyeBrowMakeUp;
import Categories.Beauty.EyeMakeUp;
import Categories.Books.Children_Book;
import Categories.Books.Fiction_Book;
import Categories.Books.Poetry_Book;
import Categories.Clothes.Coat;
import Categories.Clothes.Jean;
import Categories.Clothes.Sweater;
import Categories.Electronics.Laptop;
import Categories.Electronics.SmartPhone;
import Categories.Electronics.SmartWatch;
import Categories.Home.AirConditioner;
import Categories.Home.Refrigerator;
import Categories.Home.TV;
import Categories.Sports.Ball;
import Categories.Sports.Gloves;
import Categories.Sports.Rackets;
import Categories.SuperMarket.Dairy;
import Categories.SuperMarket.Drinks;
import Categories.SuperMarket.Proteins;
import Categories.Tools.Drill;
import Categories.Tools.SolderingSystem;
import Categories.Tools.Spanner;
import Categories.ToysAndGames.BoardGames;
import Categories.ToysAndGames.CardGames;
import Categories.ToysAndGames.Puzzles;
import Categories.Vehicles.Car;
import Categories.Vehicles.Motorcycle;
import Categories.Vehicles.Truck;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static Connection.Connect.connect;

public class WalletReq {
    private final double value;
    private final UUID userID;
    private final UUID walletID;
    private boolean isConfirmed;

    //Constructor

    public WalletReq(double value, UUID userID) {
        this.value = value;
        this.isConfirmed = false;
        this.userID = userID;
        this.walletID = UUID.randomUUID();
        insert();
    }

    public WalletReq(double value, UUID userID, UUID walletID, boolean isConfirmed) {
        this.value = value;
        this.userID = userID;
        this.walletID = walletID;
        this.isConfirmed = isConfirmed;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO WalletRequest(value, userID, WalletRequestID, isConfirmed) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, value);
            pstmt.setString(2, userID.toString());
            pstmt.setString(3, walletID.toString());
            pstmt.setString(4, Boolean.toString(isConfirmed));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double getValue() {
        return value;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public UUID getUser() {
        return userID;
    }

    public UUID getWalletID() {
        return walletID;
    }

    public void setConfirmed() {
        this.isConfirmed = true;
        updateWalletRequestInDatabase();
    }

    @Override
    public String toString() {
        return "WalletReq{" +
                "value=" + value +
                ", userID=" + userID +
                ", walletID=" + walletID +
                ", isConfirmed=" + isConfirmed +
                '}';
    }

    //Database - Related methods

    public void updateWalletRequestInDatabase() {
        String sql = "UPDATE WalletRequest SET isConfirmed = ? WHERE WalletRequestID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Boolean.toString(true));
            stmt.setString(2, walletID.toString());
            stmt.executeUpdate();
            System.out.println("Wallet has been confirmed in the database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadWalletRequestsFromDatabase(Shop shop){
        String sql = "SELECT * FROM WalletRequest";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                double value = rs.getDouble("value");
                UUID userID = UUID.fromString(rs.getString("userID"));
                UUID walletID = UUID.fromString(rs.getString("WalletRequestID"));
                boolean isConfirmed = Boolean.parseBoolean(rs.getString("isConfirmed"));
                WalletReq newWalletReq = new WalletReq(value, userID, walletID, isConfirmed);
                shop.submitAWalletRequestInShopOnly(newWalletReq);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
