package Categories.Sports;

import Accounts.Seller;
import Categories.Product;

import java.util.ArrayList;

public class Sports extends Product {
    private final double weight;
    private final String sportType;
    private final String brand;

    //Constructor

    public Sports(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, double weight, String sportType, String brand) {
        super(name, color, quantity, price, seller, comments);
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
