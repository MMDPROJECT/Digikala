package Categories.Beauty;

import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;

public class EyeMakeUp extends Beauty{
    private PenType penType;
    private boolean hasWaterResistance;
    private String brand;
    private int longevity;

    //Constructor

    public EyeMakeUp(String name, double price, String color, int quantity, MatterState materialState, boolean hasBox, PenType penType, boolean hasWaterResistance, String brand, int longevity) {
        super(name, price, color, quantity, materialState, hasBox);
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
