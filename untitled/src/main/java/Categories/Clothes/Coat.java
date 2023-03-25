package Categories.Clothes;

import Accounts.Seller;
import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

import java.util.ArrayList;

public class Coat extends Clothes {
    private final int buttonNumber;
    private final boolean hasCap;

    //Constructor

    public Coat(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, boolean hasCap) {
        super(name, color, quantity, price, seller, comments, size, gender, material, brand, durability);
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
