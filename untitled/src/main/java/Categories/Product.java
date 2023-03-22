package Categories;

import java.util.ArrayList;

public abstract class Product {
    private String name;
    private String color;
    private int quantity;
    private double price;
    ArrayList<String> comments;

    //Constructor
    public Product (String name, double price, String color, int quantity){
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
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

    //Product - Related Functions
    public void showComments(){
        if (this.comments.size() == 0){
            System.out.println("No comment has been submitted for this product yet!\n");
        }
        else {
            System.out.println("Comments for this product:\n");
            for (String comment : this.comments){
                System.out.println("- " + comment);
            }
        }
    }

    public void submitComment(String newComment){
        this.comments.add(newComment);
    }

    public void addProduct(int quantity){
        this.quantity += quantity;
    }

    //Override

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", comments=" + comments +
                '}';
    }
}
