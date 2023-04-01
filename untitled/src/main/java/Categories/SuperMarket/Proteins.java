package Categories.SuperMarket;

import Categories.SuperMarket.Enums.ProteinProductType;

import java.util.ArrayList;
import java.util.UUID;

public class Proteins extends SuperMarket {
    private final String brand;
    private final double protein;
    private final ProteinProductType productType;

    //Constructor

    public Proteins(String name, String color, int quantity, double price, UUID sellerID, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, String brand, double protein, ProteinProductType productType) {
        super(name, color, quantity, price, sellerID, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
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
