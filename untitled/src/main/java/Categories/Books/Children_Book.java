package Categories.Books;

import Connection.Connect;
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
        insert();
    }

    public Children_Book(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String ISBN, int pageNumbers, String author, String language, String readingLevel, String theme) {
        super(comments, id, name, color, price, sellerId, quantity, ISBN, pageNumbers, author, language);
        this.readingLevel = readingLevel;
        this.theme = theme;
    }

    //Override

    //Getters and Setters
    public String getReadingLevel() {
        return readingLevel;
    }

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

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ISBN, pageNumbers, author, language, readingLevel, theme, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, getISBN());
            pstmt.setInt(9, getPageNumbers());
            pstmt.setString(10, getAuthor());
            pstmt.setString(11, getLanguage());
            pstmt.setString(12, readingLevel);
            pstmt.setString(13, theme);
            pstmt.setString(14, "Children_Book");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
