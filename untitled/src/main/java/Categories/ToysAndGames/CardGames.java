package Categories.ToysAndGames;

import Accounts.Seller;
import Categories.ToysAndGames.Enums.DifficultyLevel;

import java.util.ArrayList;

public class CardGames extends ToysAndGames{
    private int cardNumber;
    private int playerNumber;
    private int gangNumber;     //for example a game could have 4 gangs.

    //Constructor

    public CardGames(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasBox, DifficultyLevel difficultyLevel, boolean isMultiplayer, int cardNumber, int playerNumber, int gangNumber) {
        super(name, color, quantity, price, seller, comments, hasBox, difficultyLevel, isMultiplayer);
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
