package Categories.SuperMarket.Vehicles;

import Categories.SuperMarket.Vehicles.Enums.NoiseLevel;

public class Motorcycle extends Vehicles {
    private int seatNumber;
    private boolean hasWingMirror;
    private NoiseLevel noiseLevel;

    //Constructor

    public Motorcycle(String name, double price, String color, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, int seatNumber, boolean hasWingMirror, NoiseLevel noiseLevel) {
        super(name, price, color, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
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
