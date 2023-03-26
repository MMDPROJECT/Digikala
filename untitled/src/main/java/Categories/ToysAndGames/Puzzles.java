package Categories.ToysAndGames;

import Accounts.Seller;
import Categories.ToysAndGames.Enums.DifficultyLevel;

public class Puzzles extends ToysAndGames {
    private final int partNumber;
    private final String finalPicture;

    //Constructor

    public Puzzles(String name, String color, int quantity, double price, Seller seller, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(name, color, quantity, price, seller, hasBox, difficultyLevel, isMultiplayer);
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
