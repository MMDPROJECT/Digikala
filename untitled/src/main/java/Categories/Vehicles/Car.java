package Categories.Vehicles;

import Accounts.Seller;

import java.util.ArrayList;

public class Car extends Vehicles {
    private final boolean isRightSteering;
    private final String speakerModel;
    private final int seatNumber;

    //Constructor

    public Car(String name, String color, int quantity, double price, Seller seller, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        super(name, color, quantity, price, seller, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.isRightSteering = isRightSteering;
        this.speakerModel = speakerModel;
        this.seatNumber = seatNumber;
    }


    //Getters and Setters

    public boolean isRightSteering() {
        return isRightSteering;
    }

    public String getSpeakerModel() {
        return speakerModel;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    //Override

    @Override
    public String toString() {
        return "Car{" +
                "isRightSteering=" + isRightSteering +
                ", speakerModel='" + speakerModel + '\'' +
                ", seatNumber=" + seatNumber +
                "} " + super.toString();
    }
}
