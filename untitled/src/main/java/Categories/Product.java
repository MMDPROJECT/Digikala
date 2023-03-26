package Categories;

import Accounts.Seller;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private final String name;
    private final String color;
    private final double price;
    private final Seller seller;
    ArrayList<String> comments;
    private int quantity;

    //Constructor


    public Product(String name, String color, int quantity, double price, Seller seller) {
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.id = UUID.randomUUID();
        this.seller = seller;
        this.comments = new ArrayList<>();
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

    public Seller getSeller() {
        return seller;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public UUID getId() {
        return id;
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
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", available stock=" + quantity +
                ", price=" + price +
                ", id=" + id +
                ", seller=" + seller +
                '}';
    }
}
