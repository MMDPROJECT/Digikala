package Categories.Home;

import Categories.Home.Enums.RefrigeratorType;

public class Refrigerator extends Home{
    private int floorNumber;
    private boolean hasFridge;
    private RefrigeratorType refrigeratorType;
    private boolean hasDigitalControllingSystem;

    //Constructor

    public Refrigerator(String name, double price, String color, int quantity, boolean hasController, double height, double width, double weight, int floorNumber, boolean hasFridge, RefrigeratorType refrigeratorType, boolean hasDigitalControllingSystem) {
        super(name, price, color, quantity, hasController, height, width, weight);
        this.floorNumber = floorNumber;
        this.hasFridge = hasFridge;
        this.refrigeratorType = refrigeratorType;
        this.hasDigitalControllingSystem = hasDigitalControllingSystem;
    }


    //Getters and Setters

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isHasFridge() {
        return hasFridge;
    }

    public RefrigeratorType getRefrigeratorType() {
        return refrigeratorType;
    }

    public boolean isHasDigitalControllingSystem() {
        return hasDigitalControllingSystem;
    }

    //Override

    @Override
    public String toString() {
        return "Refrigerator{" +
                "floorNumber=" + floorNumber +
                ", hasFridge=" + hasFridge +
                ", refrigeratorType=" + refrigeratorType +
                ", hasDigitalControllingSystem=" + hasDigitalControllingSystem +
                "} " + super.toString();
    }
}
