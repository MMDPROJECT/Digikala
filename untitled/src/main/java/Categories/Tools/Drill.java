package Categories.Tools;

import Categories.Tools.Enums.PowerSource;

public class Drill extends Tools{
    private int voltage;
    private PowerSource powerSource;
    private int minSpinSpeed;
    private int maxSpinSpeed;

    //Constructor

    public Drill(String name, double price, String color, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, int minSpinSpeed, int maxSpinSpeed) {
        super(name, price, color, quantity, weight, hasBox, isSilent, isChargeable, brand);
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
