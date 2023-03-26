package Categories.Electronics;

import Accounts.Seller;

import java.util.ArrayList;

public class SmartWatch extends Electronics {
    private final String processor;
    private final boolean hasHeartRateTracker;
    private final boolean hasStepTracker;
    private final boolean hasCaloricTracker;

    //Constructor

    public SmartWatch(String name, String color, int quantity, double price, Seller seller, String brand, String model, String OS, String screenSize, double batteryCapacity, String processor, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        super(name, color, quantity, price, seller, brand, model, OS, screenSize, batteryCapacity);
        this.processor = processor;
        this.hasHeartRateTracker = hasHeartRateTracker;
        this.hasStepTracker = hasStepTracker;
        this.hasCaloricTracker = hasCaloricTracker;
    }


    //Getters and Setters

    public String getProcessor() {
        return processor;
    }

    public boolean isHasHeartRateTracker() {
        return hasHeartRateTracker;
    }

    public boolean isHasStepTracker() {
        return hasStepTracker;
    }

    public boolean isHasCaloricTracker() {
        return hasCaloricTracker;
    }

    //Override

    @Override
    public String toString() {
        return "SmartWatch{" +
                "processor='" + processor + '\'' +
                ", hasHeartRateTracker=" + hasHeartRateTracker +
                ", hasStepTracker=" + hasStepTracker +
                ", hasCaloricTracker=" + hasCaloricTracker +
                "} " + super.toString();
    }
}
