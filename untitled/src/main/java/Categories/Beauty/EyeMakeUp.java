package Categories.Beauty;

import Accounts.Seller;
import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;

import java.util.ArrayList;
import java.util.UUID;

public class EyeMakeUp extends Beauty{
    private PenType penType;
    private boolean hasWaterResistance;
    private String brand;
    private int longevity;

    //Constructor

    public EyeMakeUp(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(name, color, quantity, price, seller, comments, materialState, hasBox);
        this.penType = penType;
        this.hasWaterResistance = hasWaterResistance;
        this.brand = brand;
        this.longevity = longevity;
    }


    //Getters and Setters

    public PenType getPenType() {
        return penType;
    }

    public boolean isHasWaterResistance() {
        return hasWaterResistance;
    }

    public String getBrand() {
        return brand;
    }

    public int getLongevity() {
        return longevity;
    }

    //Override

    @Override
    public String toString() {
        return "EyeMakeUp{" +
                "penType=" + penType +
                ", hasWaterResistance=" + hasWaterResistance +
                ", brand='" + brand + '\'' +
                ", longevity=" + longevity +
                "} " + super.toString();
    }
}
