package Categories.Vehicles;

import Accounts.Seller;
import Categories.Vehicles.Enums.TruckType;

public class Truck extends Vehicles {
    private final TruckType truckType;
    private final boolean hasBed;

    //Constructor

    public Truck(String name, String color, int quantity, double price, Seller seller, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        super(name, color, quantity, price, seller, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.truckType = truckType;
        this.hasBed = hasBed;
    }


    //Getters and Setters

    public TruckType getTruckType() {
        return truckType;
    }

    public boolean isHasBed() {
        return hasBed;
    }

    //Override

    @Override
    public String toString() {
        return "Truck{" +
                "truckType=" + truckType +
                ", hasBed=" + hasBed +
                "} " + super.toString();
    }
}