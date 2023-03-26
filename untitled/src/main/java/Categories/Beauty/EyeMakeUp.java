package Categories.Beauty;

import Accounts.Seller;
import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;

public class EyeMakeUp extends Beauty {
    private final PenType penType;
    private final boolean hasWaterResistance;
    private final String brand;
    private final int longevity;

    //Constructor

    public EyeMakeUp(String name, String color, int quantity, double price, Seller seller, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(name, color, quantity, price, seller, materialState, hasBox);
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
