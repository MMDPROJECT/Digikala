package Categories.Sports;

import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Sports extends Product {
    private final double weight;
    private final String sportType;
    private final String brand;

    //Constructor

    public Sports(String name, String color, int quantity, double price, UUID sellerID, double weight, String sportType, String brand) {
        super(name, color, quantity, price, sellerID);
        this.weight = weight;
        this.sportType = sportType;
        this.brand = brand;
    }

    public Sports(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, String sportType, String brand) {
        super(comments, id, name, color, price, sellerId, quantity);
        this.weight = weight;
        this.sportType = sportType;
        this.brand = brand;
    }

    //Getters and Setters

    public double getWeight() {
        return weight;
    }

    public String getSportType() {
        return sportType;
    }

    public String getBrand() {
        return brand;
    }

    //Override

    @Override
    public String toString() {
        return "Sports{" +
                "weight=" + weight +
                ", sportType='" + sportType + '\'' +
                ", brand='" + brand + '\'' +
                "} " + super.toString();
    }
}
