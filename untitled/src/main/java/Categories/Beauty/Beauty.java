package Categories.Beauty;

import Accounts.Seller;
import Categories.Beauty.Enums.MatterState;
import Categories.Product;

import java.util.ArrayList;

public class Beauty extends Product {
    private MatterState materialState;
    private boolean hasBox;

    //Constructor

    public Beauty(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, MatterState materialState, boolean hasBox) {
        super(name, color, quantity, price, seller, comments);
        this.materialState = materialState;
        this.hasBox = hasBox;
    }


    //Getters and Setters

    public MatterState getMaterialState() {
        return materialState;
    }

    public boolean isHasBox() {
        return hasBox;
    }

    //Override

    @Override
    public String toString() {
        return "Beauty{" +
                "materialState=" + materialState +
                ", hasBox=" + hasBox +
                "} " + super.toString();
    }
}
