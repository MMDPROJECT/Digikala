package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.UUID;

public class Puzzles extends ToysAndGames {
    private final int partNumber;
    private final String finalPicture;

    //Constructor

    public Puzzles(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
        this.partNumber = partNumber;
        this.finalPicture = finalPicture;
    }


    //Getters and Setters

    public int getPartNumber() {
        return partNumber;
    }

    public String getFinalPicture() {
        return finalPicture;
    }

    //Override

    @Override
    public String toString() {
        return "Puzzles{" +
                "partNumber=" + partNumber +
                ", finalPicture='" + finalPicture + '\'' +
                "} " + super.toString();
    }
}
