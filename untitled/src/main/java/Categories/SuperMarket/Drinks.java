package Categories.SuperMarket;

import Categories.SuperMarket.Enums.DrinkSize;

import java.util.ArrayList;

public class Drinks extends SuperMarket{
    private String taste;
    private boolean isSoftDrink;
    private double litters;
    private DrinkSize size;

    //Constructor


    public Drinks(String name, double price, String color, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String taste, boolean isSoftDrink, double litters, DrinkSize size) {
        super(name, price, color, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
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
