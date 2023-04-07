package Categories.Tools;

import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Tools extends Product {
    private final double weight;
    private final boolean hasBox;
    private final boolean isSilent;
    private final boolean isChargeable;
    private final String brand;

    //Constructor

    public Tools(String name, String color, int quantity, double price, UUID sellerID, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand) {
        super(name, color, quantity, price, sellerID);
        this.weight = weight;
        this.hasBox = hasBox;
        this.isSilent = isSilent;
        this.isChargeable = isChargeable;
        this.brand = brand;
    }

    public Tools(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand) {
        super(comments, id, name, color, price, sellerId, quantity);
        this.weight = weight;
        this.hasBox = hasBox;
        this.isSilent = isSilent;
        this.isChargeable = isChargeable;
        this.brand = brand;
    }

    //Getters and Setters

    public double getWeight() {
        return weight;
    }

    public boolean isHasBox() {
        return hasBox;
    }

    public boolean isSilent() {
        return isSilent;
    }

    public boolean isChargeable() {
        return isChargeable;
    }

    public String getBrand() {
        return brand;
    }

    //Override

    @Override
    public String toString() {
        return "Tools{" +
                "weight=" + weight +
                ", hasBox=" + hasBox +
                ", isSilent=" + isSilent +
                ", isChargeable=" + isChargeable +
                ", brand='" + brand + '\'' +
                "} " + super.toString();
    }
}
