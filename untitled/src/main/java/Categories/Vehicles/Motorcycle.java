package Categories.Vehicles;

import Categories.Vehicles.Enums.NoiseLevel;

import java.util.UUID;

public class Motorcycle extends Vehicles {
    private final int seatNumber;
    private final boolean hasWingMirror;
    private final NoiseLevel noiseLevel;

    //Constructor

    public Motorcycle(String name, String color, int quantity, double price, UUID sellerID, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, int seatNumber, boolean hasWingMirror, NoiseLevel noiseLevel) {
        super(name, color, quantity, price, sellerID, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
        this.seatNumber = seatNumber;
        this.hasWingMirror = hasWingMirror;
        this.noiseLevel = noiseLevel;
    }


    //Getters and Setters

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isHasWingMirror() {
        return hasWingMirror;
    }

    public NoiseLevel getNoiseLevel() {
        return noiseLevel;
    }

    //Override

    @Override
    public String toString() {
        return "Motorcycle{" +
                "seatNumber=" + seatNumber +
                ", hasWingMirror=" + hasWingMirror +
                ", noiseLevel=" + noiseLevel +
                "} " + super.toString();
    }
}
