package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;
import Connection.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class BoardGames extends ToysAndGames {
    private final String size;    //Example: 2 * 2, 9 * 9
    private final int playerNumber;
    private final int timeToFinish;   //Example 60 (minutes)

    //Constructor

    public BoardGames(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, int playerNumber, int timeToFinish) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
        this.size = size;
        this.playerNumber = playerNumber;
        this.timeToFinish = timeToFinish;
        insert();
    }

    public BoardGames(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, int playerNumber, int timeToFinish) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.size = size;
        this.playerNumber = playerNumber;
        this.timeToFinish = timeToFinish;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, size, playerNumber, timeToFinish, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(isHasBox()));
            pstmt.setString(9, getDifficultyLevel().toString());
            pstmt.setString(10, Boolean.toString(isMultiplayer()));
            pstmt.setString(11, size);
            pstmt.setInt(12, playerNumber);
            pstmt.setInt(13, timeToFinish);
            pstmt.setString(14, "BoardGames");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSize() {
        return size;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    //Override

    public int getTimeToFinish() {
        return timeToFinish;
    }

    @Override
    public String toString() {
        return "BoardGames{" +
                "size='" + size + '\'' +
                ", playerNumber='" + playerNumber + '\'' +
                ", timeToFinish=" + timeToFinish +
                "} " + super.toString();
    }
}
