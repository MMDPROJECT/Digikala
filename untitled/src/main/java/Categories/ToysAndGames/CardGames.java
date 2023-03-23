package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

public class CardGames extends ToysAndGames{
    private int cardNumber;
    private int playerNumber;
    private int gangNumber;     //for example a game could have 4 gangs.

    //Constructor

    public CardGames(String name, double price, String color, int quantity, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        super(name, price, color, quantity, hasBox, difficultyLevel, isMultiplayer);
        this.cardNumber = cardNumber;
        this.playerNumber = playerNumber;
        this.gangNumber = gangNumber;
    }

    //Getters and Setters

    public int getCardNumber() {
        return cardNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getGangNumber() {
        return gangNumber;
    }

    //Override

    @Override
    public String toString() {
        return "CardGames{" +
                "cardNumber=" + cardNumber +
                ", playerNumber=" + playerNumber +
                ", gangNumber=" + gangNumber +
                "} " + super.toString();
    }
}
