package Categories.Clothes;

import Accounts.Seller;
import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

import java.util.ArrayList;

public class Sweater extends Clothes {
    private int buttonNumber;

    //Constructor

    public Sweater(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber) {
        super(name, color, quantity, price, seller, comments, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
    }


    //Getter and Setters

    public int getButtonNumber() {
        return buttonNumber;
    }

    //Override

    @Override
    public String toString() {
        return "Sweater{" +
                "buttonNumber=" + buttonNumber +
                "} " + super.toString();
    }
}
