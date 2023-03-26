package Categories.Vehicles;

import Accounts.Seller;
import Categories.Product;

public class Vehicles extends Product {
    private final double weight;
    private final int horsePower;
    private final String engineModel;
    private final int wheelNumber;
    private final boolean isAutomatic;
    private final int maxSpeed;
    private final String brand;
    private final String model;

    //Constructor

    public Vehicles(String name, String color, int quantity, double price, Seller seller, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model) {
        super(name, color, quantity, price, seller);
        this.weight = weight;
        this.horsePower = horsePower;
        this.engineModel = engineModel;
        this.wheelNumber = wheelNumber;
        this.isAutomatic = isAutomatic;
        this.maxSpeed = maxSpeed;
        this.brand = brand;
        this.model = model;
    }

    //Getters and Setters

    public int getWheelNumber() {
        return wheelNumber;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getEngineModel() {
        return engineModel;
    }
    //Override

    @Override
    public String toString() {
        return "Vehicles{" +
                "weight=" + weight +
                ", horsePower=" + horsePower +
                ", engineModel='" + engineModel + '\'' +
                ", wheelNumber=" + wheelNumber +
                ", isAutomatic=" + isAutomatic +
                ", maxSpeed=" + maxSpeed +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                "} " + super.toString();
    }
}
