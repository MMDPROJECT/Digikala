package Categories.Vehicles;

import Categories.Vehicles.Enums.TruckType;

public class Truck extends Vehicles{
    private TruckType truckType;
    private boolean hasBed;

    //Constructor

    public Truck(String name, double price, String color, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, TruckType truckType, boolean hasBed) {
        super(name, price, color, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
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
