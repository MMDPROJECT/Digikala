package Categories.SuperMarket;

import Accounts.Seller;
import Categories.SuperMarket.Enums.DairyGroups;

import java.util.ArrayList;

public class Dairy extends SuperMarket{
    private boolean isDomestic;
    private DairyGroups dairyGroup;

    //Constructor

    public Dairy(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasBox, double weight, double salt, double calories, double fat, double sugar, ArrayList<String> ingredientItems, String countryOfOrigin, boolean isDomestic, DairyGroups dairyGroup) {
        super(name, color, quantity, price, seller, comments, hasBox, weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin);
        this.isDomestic = isDomestic;
        this.dairyGroup = dairyGroup;
    }


    //Getters and Setters

    public boolean isDomestic() {
        return isDomestic;
    }

    public DairyGroups getDairyGroup() {
        return dairyGroup;
    }

    //Override

    @Override
    public String toString() {
        return "Dairy{" +
                "isDomestic=" + isDomestic +
                ", dairyGroup=" + dairyGroup +
                "} " + super.toString();
    }
}
