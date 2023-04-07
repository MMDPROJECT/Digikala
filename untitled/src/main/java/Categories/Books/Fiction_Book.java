package Categories.Books;

import Connection.Connect;
import Shop.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        insert();
    }

    public Fiction_Book(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String ISBN, int pageNumbers, String author, String language, String tone, ArrayList<String> characters) {
        super(comments, id, name, color, price, sellerId, quantity, ISBN, pageNumbers, author, language);
        this.tone = tone;
        this.characters = characters;
    }

    //Getter and Setters
    public ArrayList<String> getCharacters() {
        return characters;
    }

    public String getTone() {
        return tone;
    }

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

    //Database - Related methods

    public void insert() {
        String sql = "INSERT INTO Products(ProductID, name, color, price, sellerID, quantity, comments, ISBN, pageNumbers, author, language, tone, characters, subCategory) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(12, tone);
            JSONObject jsonCharacters = new JSONObject();
            jsonCharacters.put("characters", new JSONArray(characters));
            String strCharacters = jsonCharacters.toString();
            pstmt.setString(13, strCharacters);
            pstmt.setString(14, "Fiction_Book");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadFictionBookFromDatabase(ResultSet rs, Shop shop){
        try {
            // loop through the result set
            UUID productID = UUID.fromString(rs.getString("ProductID"));
            UUID sellerID = UUID.fromString(rs.getString("sellerID"));
            String name = rs.getString("name");
            String color = rs.getString("color");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            JSONObject jsonComments = new JSONObject(rs.getString("comments"));
            JSONArray jsonArray1 = jsonComments.getJSONArray("comments");
            ArrayList<String> comments = new ArrayList<>();
            for (int i = 0; i < jsonArray1.length(); i++) {
                comments.add(jsonArray1.getString(i));
            }
            String ISBN = rs.getString("ISBN");
            int pageNumbers = rs.getInt("pageNumbers");
            String author = rs.getString("author");
            String language = rs.getString("language");
            String tone = rs.getString("tone");
            JSONObject jsonCharacters = new JSONObject(rs.getString("characters"));
            JSONArray jsonArray2 = jsonCharacters.getJSONArray("characters");
            ArrayList<String> characters = new ArrayList<>();
            for (int i = 0; i < jsonArray2.length(); i++) {
                characters.add(jsonArray2.getString(i));
            }
            Fiction_Book newFictionBook = new Fiction_Book(comments, productID, name, color, price, sellerID, quantity, ISBN, pageNumbers, author, language, tone, characters);
            shop.addProductToShopOnly(newFictionBook);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
