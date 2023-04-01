package Categories.Home;

import Categories.Product;

import java.util.UUID;

public class Home extends Product {
    private final boolean hasController;
    private final double height;
    private final double width;
    private final double weight;

    //Constructor

    public Home(String name, String color, int quantity, double price, UUID sellerID, boolean hasController, double height, double width, double weight) {
        super(name, color, quantity, price, sellerID);
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
