package Categories.Sports;

import Accounts.Seller;
import Categories.Sports.Enums.GloveMaterial;
import Categories.Sports.Enums.GloveSize;
import Categories.Sports.Enums.GloveStyle;
import Categories.Sports.Enums.GloveUser;

public class Gloves extends Sports {
    private final GloveMaterial material;
    private final GloveSize size;
    private final GloveUser suggestedUser;
    private final GloveStyle style;

    //Constructor

    public Gloves(String name, String color, int quantity, double price, Seller seller, double weight, String sportType, String brand, GloveMaterial material, GloveSize size, GloveUser suggestedUser, GloveStyle style) {
        super(name, color, quantity, price, seller, weight, sportType, brand);
        this.material = material;
        this.size = size;
        this.suggestedUser = suggestedUser;
        this.style = style;
    }


    //Getters and Setters

    public GloveMaterial getMaterial() {
        return material;
    }

    public GloveSize getSize() {
        return size;
    }

    public GloveUser getSuggestedUser() {
        return suggestedUser;
    }

    public GloveStyle getStyle() {
        return style;
    }

    //Override

    @Override
    public String toString() {
        return "Gloves{" +
                "material=" + material +
                ", size=" + size +
                ", suggestedUser=" + suggestedUser +
                ", style=" + style +
                "} " + super.toString();
    }
}
