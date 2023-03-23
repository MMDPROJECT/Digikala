package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

public class BoardGames extends ToysAndGames{
    private String size;    //Example: 2 * 2, 9 * 9
    private String playerNumber;
    private int timeToFinish;   //Example 60 (minutes)

    //Constructor

    public BoardGames(String name, double price, String color, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, String size, String playerNumber, int timeToFinish) {
        super(name, price, color, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.size = size;
        this.playerNumber = playerNumber;
        this.timeToFinish = timeToFinish;
    }

    //Getters and Setters

    public String getSize() {
        return size;
    }

    public String getPlayerNumber() {
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
