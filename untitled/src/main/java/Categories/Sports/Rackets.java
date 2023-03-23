package Categories.Sports;

import Categories.Sports.Enums.RacketDurability;

public class Rackets extends Sports{
    private double length;
    private double width;
    private RacketDurability durability;
    private String Shape;

    //Constructor

    public Rackets(String name, double price, String color, int quantity, double weight, String sportType, String brand, double length, double width, RacketDurability durability, String shape) {
        super(name, price, color, quantity, weight, sportType, brand);
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
