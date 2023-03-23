package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

public class Puzzles extends ToysAndGames{
    private int partNumber;
    private String finalPicture;

    //Constructor

    public Puzzles(String name, double price, String color, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int partNumber, String finalPicture) {
        super(name, price, color, quantity, hasBox, difficultyLevel, isMultiplayer);
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
