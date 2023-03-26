package Categories.Home;

import Accounts.Seller;
import Categories.Home.Enums.RefrigeratorType;

public class Refrigerator extends Home {
    private final int floorNumber;
    private final boolean hasFridge;
    private final RefrigeratorType refrigeratorType;
    private final boolean hasDigitalControllingSystem;

    //Constructor

    public Refrigerator(String name, String color, int quantity, double price, Seller seller, boolean hasController, double height, double width, double weight, int floorNumber, boolean hasFridge, RefrigeratorType refrigeratorType, boolean hasDigitalControllingSystem) {
        super(name, color, quantity, price, seller, hasController, height, width, weight);
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
