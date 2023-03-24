package Categories.Electronics;

import Accounts.Seller;

import java.util.ArrayList;

public class Laptop extends Electronics {
    private String webcamModel;
    private String CPU;
    private String GPU;
    private String fanNumber;
    private boolean hasKeyboardLight;
    private boolean hasFingerPrint;
    private String keyboardLanguage;
    private int portNumber;

    //Constructor

    public Laptop(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, String brand, String model, String OS, String screenSize, double batteryCapacity, String webcamModel, String CPU, String GPU, String fanNumber, boolean hasKeyboardLight, boolean hasFingerPrint, String keyboardLanguage, int portNumber) {
        super(name, color, quantity, price, seller, comments, brand, model, OS, screenSize, batteryCapacity);
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

    public String getFanNumber() {
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
