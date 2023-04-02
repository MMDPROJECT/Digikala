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

public class CardGames extends ToysAndGames {
    private final int cardNumber;
    private final int playerNumber;
    private final int gangNumber;     //for example a game could have 4 gangs.

    //Constructor

    public CardGames(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
        this.cardNumber = cardNumber;
        this.playerNumber = playerNumber;
        this.gangNumber = gangNumber;
    }


    //Getters and Setters

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getGangNumber() {
        return gangNumber;
    }

    //Override

    @Override
    public String toString() {
        return "CardGames{" +
                "cardNumber=" + cardNumber +
                ", playerNumber=" + playerNumber +
                ", gangNumber=" + gangNumber +
                "} " + super.toString();
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, cardNumber, playerNumber, gangNumber) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(11, cardNumber);
            pstmt.setInt(12, playerNumber);
            pstmt.setInt(13, gangNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
