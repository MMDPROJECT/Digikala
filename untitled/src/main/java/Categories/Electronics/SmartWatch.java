package Categories.Electronics;

public class SmartWatch extends Electronics{
    private String processor;
    private boolean hasHeartRateTracker;
    private boolean hasStepTracker;
    private boolean hasCaloricTracker;

    //Constructor

    public SmartWatch(String brand, String model, String OS, String screenSize, double batteryCapacity, String processor, boolean hasHeartRateTracker, boolean hasStepTracker, boolean hasCaloricTracker) {
        super(brand, model, OS, screenSize, batteryCapacity);
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
