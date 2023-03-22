package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

public class Coat extends Clothes{
    private int buttonNumber;
    private boolean hasCap;

    //Constructor

    public Coat(String name, double price, String color, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, boolean hasCap) {
        super(name, price, color, quantity, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.hasCap = hasCap;
    }


    //Getter and Setters

    public int getButtonNumber() {
        return buttonNumber;
    }

    public boolean isHasCap() {
        return hasCap;
    }

    //Override

    @Override
    public String toString() {
        return "Coat{" +
                "buttonNumber=" + buttonNumber +
                ", hasCap=" + hasCap +
                "} " + super.toString();
    }
}
