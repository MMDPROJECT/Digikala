package Categories.Electronics;

import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Electronics extends Product {
    private final String brand;
    private final String model;
    private final String OS;
    private final String screenSize;
    private final double batteryCapacity;

    //Constructor

    public Electronics(String name, String color, int quantity, double price, UUID sellerID, String brand, String model, String OS, String screenSize, double batteryCapacity) {
        super(name, color, quantity, price, sellerID);
        this.brand = brand;
        this.model = model;
        this.OS = OS;
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
    }

    public Electronics(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, String brand, String model, String OS, String screenSize, double batteryCapacity) {
        super(comments, id, name, color, price, sellerId, quantity);
        this.brand = brand;
        this.model = model;
        this.OS = OS;
        this.screenSize = screenSize;
        this.batteryCapacity = batteryCapacity;
    }

    //Getters and Setters

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getOS() {
        return OS;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    //Override

    @Override
    public String toString() {
        return "Electronics{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", OS='" + OS + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                "} " + super.toString();
    }
}
