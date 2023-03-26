package Categories.Electronics;

import Accounts.Seller;

public class Laptop extends Electronics {
    private final String webcamModel;
    private final String CPU;
    private final String GPU;
    private final int fanNumber;
    private final boolean hasKeyboardLight;
    private final boolean hasFingerPrint;
    private final String keyboardLanguage;
    private final int portNumber;

    //Constructor

    public Laptop(String name, String color, int quantity, double price, Seller seller, String brand, String model, String OS, String screenSize, double batteryCapacity, String webcamModel, String CPU, String GPU, int fanNumber, boolean hasKeyboardLight, boolean hasFingerPrint, String keyboardLanguage, int portNumber) {
        super(name, color, quantity, price, seller, brand, model, OS, screenSize, batteryCapacity);
        this.webcamModel = webcamModel;
        this.CPU = CPU;
        this.GPU = GPU;
        this.fanNumber = fanNumber;
        this.hasKeyboardLight = hasKeyboardLight;
        this.hasFingerPrint = hasFingerPrint;
        this.keyboardLanguage = keyboardLanguage;
        this.portNumber = portNumber;
    }

    //Getters and Setters

    public String getWebcamModel() {
        return webcamModel;
    }

    public String getCPU() {
        return CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public int getFanNumber() {
        return fanNumber;
    }

    public boolean isHasKeyboardLight() {
        return hasKeyboardLight;
    }

    public boolean isHasFingerPrint() {
        return hasFingerPrint;
    }

    public String getKeyboardLanguage() {
        return keyboardLanguage;
    }

    public int getPortNumber() {
        return portNumber;
    }

    //Override

    @Override
    public String toString() {
        return "Laptop{" +
                "webcamModel='" + webcamModel + '\'' +
                ", CPU='" + CPU + '\'' +
                ", GPU='" + GPU + '\'' +
                ", fanNumber='" + fanNumber + '\'' +
                ", hasKeyboardLight=" + hasKeyboardLight +
                ", hasFingerPrint=" + hasFingerPrint +
                ", keyboardLanguage='" + keyboardLanguage + '\'' +
                ", portNumber=" + portNumber +
                "} " + super.toString();
    }
}
