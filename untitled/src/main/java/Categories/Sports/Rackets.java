package Categories.Sports;

import Accounts.Seller;
import Categories.Sports.Enums.RacketDurability;

import java.util.ArrayList;

public class Rackets extends Sports{
    private double length;
    private double width;
    private RacketDurability durability;
    private String Shape;

    //Constructor

    public Rackets(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String shape) {
        super(name, color, quantity, price, seller, comments, weight, sportType, brand);
        this.length = length;
        this.width = width;
        this.durability = durability;
        Shape = shape;
    }


    //Getters and Setters

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public RacketDurability getDurability() {
        return durability;
    }

    public String getShape() {
        return Shape;
    }

    //Override

    @Override
    public String toString() {
        return "Rackets{" +
                "length=" + length +
                ", width=" + width +
                ", durability=" + durability +
                ", Shape='" + Shape + '\'' +
                "} " + super.toString();
    }
}
