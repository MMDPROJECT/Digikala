package Categories.Tools;

import Categories.Tools.Enums.SpannerMaterial;

public class Spanner extends Tools{
    private int size;
    private String style;   //Example: Combination open end / 12 point / 15° / Offset ring end
    private SpannerMaterial material;

    //Constructor

    public Spanner(String name, double price, String color, int quantity, double weight, boolean hasBox, boolean isSilent, boolean isChargeable, String brand, int size, String style, SpannerMaterial material) {
        super(name, price, color, quantity, weight, hasBox, isSilent, isChargeable, brand);
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
