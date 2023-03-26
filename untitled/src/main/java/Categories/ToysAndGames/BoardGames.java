package Categories.ToysAndGames;

import Accounts.Seller;
import Categories.ToysAndGames.Enums.DifficultyLevel;

public class BoardGames extends ToysAndGames {
    private final String size;    //Example: 2 * 2, 9 * 9
    private final int playerNumber;
    private final int timeToFinish;   //Example 60 (minutes)

    //Constructor

    public BoardGames(String name, String color, int quantity, double price, Seller seller, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, int playerNumber, int timeToFinish) {
        super(name, color, quantity, price, seller, hasBox, difficultyLevel, isMultiplayer);
        this.size = size;
        this.playerNumber = playerNumber;
        this.timeToFinish = timeToFinish;
    }


    //Getters and Setters

    public String getSize() {
        return size;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getTimeToFinish() {
        return timeToFinish;
    }

    //Override

    @Override
    public String toString() {
        return "BoardGames{" +
                "size='" + size + '\'' +
                ", playerNumber='" + playerNumber + '\'' +
                ", timeToFinish=" + timeToFinish +
                "} " + super.toString();
    }
}
