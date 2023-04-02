package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;
import Database_Insert.Connect;
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
    }


    //Getters and Setters

    public String getSize() {
        return size;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    //Override

    @Override
    public String toString() {
        return "BoardGames{" +
                "size='" + size + '\'' +
                ", playerNumber='" + playerNumber + '\'' +
                ", timeToFinish=" + timeToFinish +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, int playerNumber, int timeToFinish) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, size, playerNumber, timeToFinish) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, Boolean.toString(hasBox));
            pstmt.setString(9, difficultyLevel.toString());
            pstmt.setString(10, Boolean.toString(isMultiplayer));
            pstmt.setString(11, size);
            pstmt.setInt(12, playerNumber);
            pstmt.setInt(13, timeToFinish);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
