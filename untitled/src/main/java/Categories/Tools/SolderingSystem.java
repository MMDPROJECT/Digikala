package Categories.Tools;

import Accounts.Seller;
import Categories.Tools.Enums.PowerSource;
import Categories.Tools.Enums.UsageLevel;

public class SolderingSystem extends Tools {
    private final int voltage;
    private final PowerSource powerSource;
    private final UsageLevel usageLevel;

    //Constructor

    public SolderingSystem(String name, String color, int quantity, double price, Seller seller, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int voltage, PowerSource powerSource, UsageLevel usageLevel) {
        super(name, color, quantity, price, seller, weight, hasBox, isSilent, isChargeable, brand);
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
