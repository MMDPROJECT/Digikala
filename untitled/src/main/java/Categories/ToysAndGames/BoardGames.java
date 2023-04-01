package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.UUID;

public class BoardGames extends ToysAndGames {
    private final String size;    //Example: 2 * 2, 9 * 9
    private final int playerNumber;
    private final int timeToFinish;   //Example 60 (minutes)

    //Constructor

    public BoardGames(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, int playerNumber, int timeToFinish) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
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
