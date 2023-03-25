package Categories.Electronics;

import Accounts.Seller;

import java.util.ArrayList;

public class SmartPhone extends Electronics {
    private final double backCameraQuality;       //Quality of the main camera in megapixels
    private final double frontCameraQuality;      //Quality of the selfie camera in megapixels
    private final int cameraNumber;
    private final int storage;
    private final double OSVersion;
    private final String displayResolution;
    private final String ringTone;
    private final String CPU;

    //Constructor

    public SmartPhone(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, String brand, String model, String OS, String screenSize, double batteryCapacity, double backCameraQuality, double frontCameraQuality, int cameraNumber, int storage, double OSVersion, String displayResolution, String ringTone, String CPU) {
        super(name, color, quantity, price, seller, comments, brand, model, OS, screenSize, batteryCapacity);
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
