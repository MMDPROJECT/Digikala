package Categories.Tools;

import Accounts.Seller;
import Categories.Tools.Enums.SpannerMaterial;

import java.util.ArrayList;

public class Spanner extends Tools {
    private final int size;
    private final String style;   //Example: Combination open end / 12 point / 15° / Offset ring end
    private final SpannerMaterial material;

    //Constructor

    public Spanner(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        super(name, color, quantity, price, seller, comments, weight, hasBox, isSilent, isChargeable, brand);
        this.size = size;
        this.style = style;
        this.material = material;
    }


    //Getters and Setters

    public int getSize() {
        return size;
    }

    public String getStyle() {
        return style;
    }

    public SpannerMaterial getMaterial() {
        return material;
    }

    //Override

    @Override
    public String toString() {
        return "Spanner{" +
                "size=" + size +
                ", style='" + style + '\'' +
                ", material=" + material +
                "} " + super.toString();
    }
}
