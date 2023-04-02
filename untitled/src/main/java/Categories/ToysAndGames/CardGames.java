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
        insert();
    }

    public CardGames(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.cardNumber = cardNumber;
        this.playerNumber = playerNumber;
        this.gangNumber = gangNumber;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, cardNumber, playerNumber, gangNumber, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(11, cardNumber);
            pstmt.setInt(12, playerNumber);
            pstmt.setInt(13, gangNumber);
            pstmt.setString(14, "CardGames");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    //Override

    public int getGangNumber() {
        return gangNumber;
    }

    @Override
    public String toString() {
        return "CardGames{" +
                "cardNumber=" + cardNumber +
                ", playerNumber=" + playerNumber +
                ", gangNumber=" + gangNumber +
                "} " + super.toString();
    }
}
