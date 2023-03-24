package Categories.Electronics;

import Accounts.Seller;
import Categories.Product;

import java.util.ArrayList;

public abstract class Electronics extends Product {
    private String brand;
    private String model;
    private String OS;
    private String screenSize;
    private double batteryCapacity;

    //Constructor

    public Electronics(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, String brand, String model, String OS, String screenSize, double batteryCapacity) {
        super(name, color, quantity, price, seller, comments);
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
