package Categories.Home;

import Accounts.Seller;
import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public class Home extends Product{
    private boolean hasController;
    private double height;
    private double width;
    private double weight;

    //Constructor

    public Home(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasController, double height, double width, double weight) {
        super(name, color, quantity, price, seller, comments);
        this.hasController = hasController;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }


    //Getters and Setters

    public boolean isHasController() {
        return hasController;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getWeight() {
        return weight;
    }

    //Override

    @Override
    public String toString() {
        return "Home{" +
                "hasController=" + hasController +
                ", height=" + height +
                ", width=" + width +
                ", weight=" + weight +
                "} " + super.toString();
    }
}
