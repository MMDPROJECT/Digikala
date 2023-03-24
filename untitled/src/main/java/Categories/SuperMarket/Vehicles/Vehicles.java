package Categories.SuperMarket.Vehicles;

import Categories.Product;

public class Vehicles extends Product {
    private double weight;
    private int horsePower;
    private String engineModel;
    private int wheelNumber;
    private boolean isAutomatic;
    private int maxSpeed;
    private String brand;
    private String model;

    //Constructor

    public Vehicles(String name, double price, String color, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model) {
        super(name, price, color, quantity);
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
