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

public class Puzzles extends ToysAndGames {
    private final int partNumber;
    private final String finalPicture;

    //Constructor

    public Puzzles(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
        this.partNumber = partNumber;
        this.finalPicture = finalPicture;
    }

    public Puzzles(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(comments, id, name, color, price, sellerId, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.partNumber = partNumber;
        this.finalPicture = finalPicture;
    }

    //Getters and Setters

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, hasBox, difficultyLevel, isMultiplayer, partNumber, finalPicture, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
