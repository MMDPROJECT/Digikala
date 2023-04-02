package Categories.Beauty;

import Categories.Beauty.Enums.MatterState;
import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public class Beauty extends Product {
    private final MatterState materialState;
    private final boolean hasBox;

    //Constructor

    public Beauty(String name, String color, int quantity, double price, UUID sellerID, MatterState materialState, boolean hasBox) {
        super(name, color, quantity, price, sellerID);
        this.materialState = materialState;
        this.hasBox = hasBox;
    }

    public Beauty(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, MatterState materialState, boolean hasBox) {
        super(comments, id, name, color, price, sellerId, quantity);
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
