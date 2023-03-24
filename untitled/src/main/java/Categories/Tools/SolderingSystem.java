package Categories.Tools;

import Accounts.Seller;
import Categories.Tools.Enums.PowerSource;
import Categories.Tools.Enums.UsageLevel;

import java.util.ArrayList;

public class SolderingSystem extends Tools {
    private int voltage;
    private PowerSource powerSource;
    private UsageLevel usageLevel;

    //Constructor

    public SolderingSystem(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, UsageLevel usageLevel) {
        super(name, color, quantity, price, seller, comments, weight, hasBox, isSilent, isChargeable, brand);
        this.voltage = voltage;
        this.powerSource = powerSource;
        this.usageLevel = usageLevel;
    }


    //Getters and Setters

    public int getVoltage() {
        return voltage;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public UsageLevel getUsageLevel() {
        return usageLevel;
    }

    //Override

    @Override
    public String toString() {
        return "SolderingSystem{" +
                "voltage=" + voltage +
                ", powerSource=" + powerSource +
                ", usageLevel=" + usageLevel +
                "} " + super.toString();
    }
}
