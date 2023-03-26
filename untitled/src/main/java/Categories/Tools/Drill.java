package Categories.Tools;

import Accounts.Seller;
import Categories.Tools.Enums.PowerSource;

import java.util.ArrayList;

public class Drill extends Tools {
    private final int voltage;
    private final PowerSource powerSource;
    private final int minSpinSpeed;
    private final int maxSpinSpeed;

    //Constructor

    public Drill(String name, String color, int quantity, double price, Seller seller, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, int minSpinSpeed, int maxSpinSpeed) {
        super(name, color, quantity, price, seller, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.minSpinSpeed = minSpinSpeed;
        this.maxSpinSpeed = maxSpinSpeed;
    }


    //Getters ana Setters

    public int getVoltage() {
        return voltage;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public int getMinSpinSpeed() {
        return minSpinSpeed;
    }

    public int getMaxSpinSpeed() {
        return maxSpinSpeed;
    }

    //Override

    @Override
    public String toString() {
        return "Drill{" +
                "voltage=" + voltage +
                ", powerSource=" + powerSource +
                ", minSpinSpeed=" + minSpinSpeed +
                ", maxSpinSpeed=" + maxSpinSpeed +
                "} " + super.toString();
    }
}
