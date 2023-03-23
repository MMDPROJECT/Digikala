package Categories.Electronics;

public abstract class Electronics {
    private String brand;
    private String model;
    private String OS;
    private String screenSize;
    private double batteryCapacity;

    //Constructor

    public Electronics(String brand, String model, String OS, String screenSize, double batteryCapacity) {
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
                '}';
    }
}
