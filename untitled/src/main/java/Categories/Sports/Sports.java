package Categories.Sports;

import Categories.Product;

public class Sports extends Product {
    private double weight;
    private String sportType;
    private String brand;

    //Constructor

    public Sports(String name, double price, String color, int quantity, double weight, String sportType, String brand) {
        super(name, price, color, quantity);
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
