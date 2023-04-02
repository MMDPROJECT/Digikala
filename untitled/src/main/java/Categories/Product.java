package Categories;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static Connection.Connect.connect;

public abstract class Product {
    final ArrayList<String> comments;
    private final UUID productID;
    private final String name;
    private final String color;
    private final double price;
    private final UUID sellerId;
    private int quantity;

    //Constructor

    public Product(String name, String color, int quantity, double price, UUID sellerId) {
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.productID = UUID.randomUUID();
        this.sellerId = sellerId;
        this.comments = new ArrayList<>();
    }

    public Product(ArrayList<String> comments, UUID productID, String name, String color, double price, UUID sellerId, int quantity) {
        this.comments = comments;
        this.productID = productID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.sellerId = sellerId;
        this.quantity = quantity;
    }

    //Getter and Setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public UUID getProductID() {
        return productID;
    }

    //Product - Related Functions
    public void showComments() {
        if (this.comments.size() == 0) {
            System.out.println("No comment has been submitted for this product yet!\n");
        } else {
            System.out.println("Comments for this product:\n");
            for (String comment : this.comments) {
                System.out.println("- " + comment);
            }
        }
    }

    public void submitComment(String newComment) {
        this.comments.add(newComment);
        updateCommentsInDatabase();
    }

    public void increaseProduct(int quantity) {
        this.quantity += quantity;
        updateProductStocksInDatabase();
    }

    public void decreaseProduct(int quantity) {
        this.quantity -= quantity;
        updateProductStocksInDatabase();
    }

    //Override

    @Override
    public String toString() {
        return "Product{" +
                "comments=" + comments +
                ", productID=" + productID +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", sellerId=" + sellerId +
                ", quantity=" + quantity +
                '}';
    }

    //Database - Related methods

    public void updateCommentsInDatabase() {
        String sql = "UPDATE Products SET comments = ? WHERE ProductID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comments", new JSONArray(comments));
            String arr = jsonObject.toString();
            stmt.setString(1, arr);
            stmt.setString(2, productID.toString());
            stmt.executeUpdate();
            System.out.println("Comment has been successfully submitted in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductStocksInDatabase() {
        String sql = "UPDATE Products SET quantity = ? WHERE ProductID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, productID.toString());
            stmt.executeUpdate();
            System.out.println("Product stocks has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
