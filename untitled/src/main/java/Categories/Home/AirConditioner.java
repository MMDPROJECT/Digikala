package Categories.Home;

import Accounts.Seller;

import java.util.ArrayList;
import java.util.UUID;

public class AirConditioner extends Home{
    private double coolingCapacity;
    private double energyEfficiency;
    private String airFilter;
    private int fanNumber;
    private boolean hasRemoteControl;
    private boolean hasTimer;

    //Constructor

    public AirConditioner(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasController, double height, double width, double weight, double coolingCapacity, double energyEfficiency, String airFilter, int fanNumber, boolean hasRemoteControl, boolean hasTimer) {
        super(name, color, quantity, price, seller, comments, hasController, height, width, weight);
        this.coolingCapacity = coolingCapacity;
        this.energyEfficiency = energyEfficiency;
        this.airFilter = airFilter;
        this.fanNumber = fanNumber;
        this.hasRemoteControl = hasRemoteControl;
        this.hasTimer = hasTimer;
    }


    //Getters and Setters

    public double getCoolingCapacity() {
        return coolingCapacity;
    }

    public double getEnergyEfficiency() {
        return energyEfficiency;
    }

    public String getAirFilter() {
        return airFilter;
    }

    public int getFanNumber() {
        return fanNumber;
    }

    public boolean isHasRemoteControl() {
        return hasRemoteControl;
    }

    public boolean isHasTimer() {
        return hasTimer;
    }

    //Override

    @Override
    public String toString() {
        return "AirConditioner{" +
                "coolingCapacity=" + coolingCapacity +
                ", energyEfficiency=" + energyEfficiency +
                ", airFilter='" + airFilter + '\'' +
                ", fanNumber=" + fanNumber +
                ", hasRemoteControl=" + hasRemoteControl +
                ", hasTimer=" + hasTimer +
                "} " + super.toString();
    }
}
