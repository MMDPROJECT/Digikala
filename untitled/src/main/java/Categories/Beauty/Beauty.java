package Categories.Beauty;

import Categories.Beauty.Enums.MatterState;
import Categories.Product;

public class Beauty extends Product {
    private MatterState materialState;
    private boolean hasBox;

    //Constructor

    public Beauty(String name, double price, String color, int quantity, MatterState materialState, boolean hasBox) {
        super(name, price, color, quantity);
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
