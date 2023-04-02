package Categories;

import java.util.ArrayList;
import java.util.UUID;

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
    }

    public void increaseProduct(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseProduct(int quantity) {
        this.quantity -= quantity;
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
}
