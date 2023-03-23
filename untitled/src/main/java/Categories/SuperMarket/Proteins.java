package Categories.SuperMarket;

import Categories.SuperMarket.Enums.ProteinProductType;

import java.util.ArrayList;

public class Proteins extends SuperMarket{
    private String brand;
    private double protein;
    private ProteinProductType productType;


    //Constructor

    public Proteins(String name, double price, String color, int quantity, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String brand, double protein, ProteinProductType productType) {
        super(name, price, color, quantity, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.brand = brand;
        this.protein = protein;
        this.productType = productType;
    }

    //Getters and Setters

    public String getBrand() {
        return brand;
    }

    public double getProtein() {
        return protein;
    }

    public ProteinProductType getProductType() {
        return productType;
    }

    //Override

    @Override
    public String toString() {
        return "Proteins{" +
                "brand='" + brand + '\'' +
                ", protein=" + protein +
                ", productType=" + productType +
                "} " + super.toString();
    }
}
