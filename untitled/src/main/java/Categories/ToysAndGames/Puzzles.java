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

public class Puzzles extends ToysAndGames {
    private final int partNumber;
    private final String finalPicture;

    //Constructor

    public Puzzles(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
        this.partNumber = partNumber;
        this.finalPicture = finalPicture;
        insert();
    }

    public Puzzles(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.partNumber = partNumber;
        this.finalPicture = finalPicture;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, partNumber, finalPicture, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setInt(11, partNumber);
            pstmt.setString(12, finalPicture);
            pstmt.setString(13, "Puzzles");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getPartNumber() {
        return partNumber;
    }

    //Override

    public String getFinalPicture() {
        return finalPicture;
    }

    @Override
    public String toString() {
        return "Puzzles{" +
                "partNumber=" + partNumber +
                ", finalPicture='" + finalPicture + '\'' +
                "} " + super.toString();
    }
}
