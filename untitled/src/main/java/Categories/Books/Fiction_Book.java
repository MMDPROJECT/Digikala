package Categories.Books;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Fiction_Book extends Books {
    private final String tone;
    private final ArrayList<String> characters;

    //Constructor

    public Fiction_Book(String name, String color, int quantity, double price, UUID sellerID, String ISBN, int pageNumbers, String author, String language, ArrayList<String> characters, String tone) {
        super(name, color, quantity, price, sellerID, ISBN, pageNumbers, author, language);
        this.characters = characters;
        this.tone = tone;
    }

    public Fiction_Book(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String ISBN, int pageNumbers, String author, String language, String tone, ArrayList<String> characters) {
        super(comments, id, name, color, price, sellerId, quantity, ISBN, pageNumbers, author, language);
        this.tone = tone;
        this.characters = characters;
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, String ISBN, int pageNumbers, String author, String language, String tone, ArrayList<String> characters) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ISBN, pageNumbers, author, language, tone, characters, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, ISBN);
            pstmt.setInt(9, pageNumbers);
            pstmt.setString(10, author);
            pstmt.setString(11, language);
            pstmt.setString(12, tone);
            JSONObject json2 = new JSONObject();
            json2.put("characters", new JSONArray(characters));
            String strCharacters = json2.toString();
            pstmt.setString(13, strCharacters);
            pstmt.setString(14, "Fiction_Book");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Getter and Setters
    public ArrayList<String> getCharacters() {
        return characters;
    }

    public String getTone() {
        return tone;
    }

    //Overrides

    //Fiction-Book - Related Functions
    public void showCharacters() {
        if (this.characters.size() == 0) {
            System.out.println("This book doesn't have any special character!\n");
        } else {
            System.out.println("List of characters:\n");
            for (String character : this.characters) {
                System.out.println("* " + character);
            }
        }
    }

    public void addCharacter(String character) {
        this.characters.add(character);
    }

    @Override
    public String toString() {
        return "Fiction_Book{" +
                "characters=" + characters +
                ", tone=" + tone +
                "} " + super.toString();
    }
}
