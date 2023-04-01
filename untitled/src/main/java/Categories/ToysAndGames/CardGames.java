package Categories.ToysAndGames;

import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.UUID;

public class CardGames extends ToysAndGames {
    private final int cardNumber;
    private final int playerNumber;
    private final int gangNumber;     //for example a game could have 4 gangs.

    //Constructor

    public CardGames(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        super(name, color, quantity, price, sellerID, hasBox, difficultyLevel, isMultiplayer);
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
