package Categories.Books;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Poetry_Book extends Books {
    private final String poeticForm;
    private final int verseNumber;

    //Constructor


    public Poetry_Book(String name, String color, int quantity, double price, UUID sellerID, String ISBN, int pageNumbers, String author, String language, String poeticForm, int verseNumber) {
        super(name, color, quantity, price, sellerID, ISBN, pageNumbers, author, language);
        this.poeticForm = poeticForm;
        this.verseNumber = verseNumber;
    }

    public Poetry_Book(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String ISBN, int pageNumbers, String author, String language, String poeticForm, int verseNumber) {
        super(comments, id, name, color, price, sellerId, quantity, ISBN, pageNumbers, author, language);
        this.poeticForm = poeticForm;
        this.verseNumber = verseNumber;
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, String ISBN, int pageNumbers, String author, String language, String poeticForm, int verseNumber) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ISBN, pageNumbers, author, language, poeticForm, verseNumber, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(12, poeticForm);
            pstmt.setInt(13, verseNumber);
            pstmt.setString(14, "Poetry_Book");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Overrides

    //Getter and Setters
    public String getPoeticForm() {
        return poeticForm;
    }

    public int getVersesNumber() {
        return verseNumber;
    }

    @Override
    public String toString() {
        return "Poetry_Book{" +
                "poeticForm=" + poeticForm +
                ", verseNumber=" + verseNumber +
                "} " + super.toString();
    }
}
