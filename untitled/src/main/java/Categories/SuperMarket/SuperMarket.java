package Categories.SuperMarket;

import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

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

    public SuperMarket(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin) {
        super(name, color, quantity, price, sellerID);
        this.hasBox = hasBox;
        this.weight = weight;
        this.salt = salt;
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        IngredientItems = ingredientItems;
        CountryOfOrigin = countryOfOrigin;
    }

    public SuperMarket(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin) {
        super(comments, id, name, color, price, sellerId, quantity);
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
