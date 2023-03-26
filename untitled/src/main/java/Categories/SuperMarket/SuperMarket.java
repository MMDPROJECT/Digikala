package Categories.SuperMarket;

import Accounts.Seller;
import Categories.Product;

import java.util.ArrayList;

public class SuperMarket extends Product {
    private final boolean hasBox;
    private final double weight;
    private final double salt;
    private final double calories;
    private final double fat;
    private final double sugar;
    private final ArrayList<String> IngredientItems;
    private final String CountryOfOrigin;

    //Constructor

    public SuperMarket(String name, String color, int quantity, double price, Seller seller, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin) {
        super(name, color, quantity, price, seller);
        this.hasBox = hasBox;
        this.weight = weight;
        this.salt = salt;
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        IngredientItems = ingredientItems;
        CountryOfOrigin = countryOfOrigin;
    }


    //Getters and Setters

    public boolean isHasBox() {
        return hasBox;
    }

    public double getWeight() {
        return weight;
    }

    public double getSalt() {
        return salt;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getSugar() {
        return sugar;
    }

    public ArrayList<String> getIngredientItems() {
        return IngredientItems;
    }

    public String getCountryOfOrigin() {
        return CountryOfOrigin;
    }

    //Override

    @Override
    public String toString() {
        return "SuperMarket{" +
                "hasBox=" + hasBox +
                ", weight=" + weight +
                ", salt=" + salt +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sugar=" + sugar +
                ", IngredientItems=" + IngredientItems +
                ", CountryOfOrigin='" + CountryOfOrigin + '\'' +
                "} " + super.toString();
    }
}
