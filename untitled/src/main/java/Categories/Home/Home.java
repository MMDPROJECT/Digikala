package Categories.Home;

import Categories.Product;

public class Home extends Product{
    private boolean hasController;
    private double height;
    private double width;
    private double weight;

    //Constructor

    public Home(String name, double price, String color, int quantity, boolean hasController, double height, double width, double weight) {
        super(name, price, color, quantity);
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
