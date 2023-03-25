package Categories.SuperMarket.Vehicles;

public class Car extends Vehicles {
    private final boolean isRightSteering;
    private final String speakerModel;
    private final int seatNumber;

    //Constructor

    public Car(String name, double price, String color, int quantity, double weight, int horsePower, String engineModel, int wheelNumber, boolean isAutomatic, int maxSpeed, String brand, String model, boolean isRightSteering, String speakerModel, int seatNumber) {
        super(name, price, color, quantity, weight, horsePower, engineModel, wheelNumber, isAutomatic, maxSpeed, brand, model);
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
