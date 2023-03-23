package Categories.Electronics;

public class SmartPhone extends Electronics{
    private double backCameraQuality;       //Quality of the main camera in megapixels
    private double frontCameraQuality;      //Quality of the selfie camera in megapixels
    private int cameraNumber;
    private int storage;
    private double OSVersion;
    private String displayResolution;
    private String ringTone;
    private String CPU;

    //Constructor

    public SmartPhone(String brand, String model, String OS, String screenSize, double batteryCapacity, double backCameraQuality, double frontCameraQuality, int cameraNumber, int storage, double OSVersion, String displayResolution, String ringTone, String CPU) {
        super(brand, model, OS, screenSize, batteryCapacity);
        this.backCameraQuality = backCameraQuality;
        this.frontCameraQuality = frontCameraQuality;
        this.cameraNumber = cameraNumber;
        this.storage = storage;
        this.OSVersion = OSVersion;
        this.displayResolution = displayResolution;
        this.ringTone = ringTone;
        this.CPU = CPU;
    }

    //Getters and Setters

    public double getBackCameraQuality() {
        return backCameraQuality;
    }

    public double getFrontCameraQuality() {
        return frontCameraQuality;
    }

    public int getCameraNumber() {
        return cameraNumber;
    }

    public int getStorage() {
        return storage;
    }

    public double getOSVersion() {
        return OSVersion;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public String getRingTone() {
        return ringTone;
    }

    public String getCPU() {
        return CPU;
    }

    //Override


    @Override
    public String toString() {
        return "SmartPhone{" +
                "backCameraQuality=" + backCameraQuality +
                ", frontCameraQuality=" + frontCameraQuality +
                ", cameraNumber=" + cameraNumber +
                ", storage=" + storage +
                ", OSVersion=" + OSVersion +
                ", displayResolution='" + displayResolution + '\'' +
                ", ringTone='" + ringTone + '\'' +
                ", CPU='" + CPU + '\'' +
                "} " + super.toString();
    }
}
