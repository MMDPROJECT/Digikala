package Categories.SuperMarket;

import Accounts.Seller;
import Categories.SuperMarket.Enums.DrinkSize;

import java.util.ArrayList;

public class Drinks extends SuperMarket {
    private final String taste;
    private final boolean isSoftDrink;
    private final double litters;
    private final DrinkSize size;

    //Constructor

    public Drinks(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String taste, boolean isSoftDrink, double litters, DrinkSize size) {
        super(name, color, quantity, price, seller, comments, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.taste = taste;
        this.isSoftDrink = isSoftDrink;
        this.litters = litters;
        this.size = size;
    }


    //Getters and Setters

    public String getTaste() {
        return taste;
    }

    public boolean isSoftDrink() {
        return isSoftDrink;
    }

    public double getLitters() {
        return litters;
    }

    public DrinkSize getSize() {
        return size;
    }

    //Override

    @Override
    public String toString() {
        return "Drinks{" +
                "taste='" + taste + '\'' +
                ", isSoftDrink=" + isSoftDrink +
                ", litters=" + litters +
                ", size=" + size +
                "} " + super.toString();
    }
}
