package Categories.ToysAndGames;

import Categories.Product;
import Categories.ToysAndGames.Enums.DifficultyLevel;

public class ToysAndGames extends Product {
    private boolean hasBox;
    private DifficultyLevel difficultyLevel;
    private boolean isMultiplayer;

    //Constructor

    public ToysAndGames(String name, double price, String color, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer) {
        super(name, price, color, quantity);
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
