package Categories.ToysAndGames;

import Accounts.Seller;
import Categories.Product;
import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.ArrayList;

public class ToysAndGames extends Product {
    private final boolean hasBox;
    private final DifficultyLevel difficultyLevel;
    private final boolean isMultiplayer;

    //Constructor

    public ToysAndGames(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer) {
        super(name, color, quantity, price, seller, comments);
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
