package Categories.ToysAndGames;

import Categories.Product;
import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.UUID;

public class ToysAndGames extends Product {
    private final boolean hasBox;
    private final DifficultyLevel difficultyLevel;
    private final boolean isMultiplayer;

    //Constructor

    public ToysAndGames(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer) {
        super(name, color, quantity, price, sellerID);
        this.hasBox = hasBox;
        this.difficultyLevel = difficultyLevel;
        this.isMultiplayer = isMultiplayer;
    }


    //Getters and Setters

    public boolean isHasBox() {
        return hasBox;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    //Override

    @Override
    public String toString() {
        return "ToysAndGames{" +
                "hasBox=" + hasBox +
                ", difficultyLevel=" + difficultyLevel +
                ", isMultiplayer=" + isMultiplayer +
                "} " + super.toString();
    }
}
