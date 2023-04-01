package Categories.Books;

import Database_Insert.Connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Children_Book extends Books {
    private final String readingLevel;
    private final String theme;

    //Constructor


    public Children_Book(String name, String color, int quantity, double price, UUID sellerID, String ISBN, int pageNumbers, String author, String language, String readingLevel, String theme) {
        super(name, color, quantity, price, sellerID, ISBN, pageNumbers, author, language);
        this.readingLevel = readingLevel;
        this.theme = theme;
    }

    public static void insert(UUID productID, String name, String color, double price, UUID sellerID, int quantity, ArrayList<String> comments, String ISBN, int pageNumbers, String author, String language, String readingLevel, String theme) {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ISBN, pageNumbers, author, language, readingLevel, theme) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(12, readingLevel);
            pstmt.setString(13, theme);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Getters and Setters
    public String getReadingLevel() {
        return readingLevel;
    }

    //Override

    public String getTheme() {
        return theme;
    }

    @Override
    public String toString() {
        return "Children_Book{" +
                "readingLevel=" + readingLevel +
                ", theme=" + theme +
                "} " + super.toString();
    }
}
