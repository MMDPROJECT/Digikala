package Categories.SuperMarket;

import Categories.Product;

import java.util.ArrayList;

public class SuperMarket extends Product {
    private boolean hasBox;
    private double weight;
    private double salt;
    private double calories;
    private double fat;
    private double sugar;
    private ArrayList<String> IngredientItems;
    private String CountryOfOrigin;

    //Constructor

    public SuperMarket(String name, double price, String color, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin) {
        super(name, price, color, quantity);
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
