package Categories.Clothes;

import Accounts.Seller;
import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

public class Sweater extends Clothes {
    private final int buttonNumber;
    private final String design;

    //Constructor

    public Sweater(String name, String color, int quantity, double price, Seller seller, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber, String design) {
        super(name, color, quantity, price, seller, size, gender, material, brand, durability);
        this.buttonNumber = buttonNumber;
        this.design = design;
    }
//Getter and Setters

    public int getButtonNumber() {
        return buttonNumber;
    }

    public String getDesign() {
        return design;
    }
    //Override

    @Override
    public String toString() {
        return "Sweater{" +
                "buttonNumber=" + buttonNumber +
                ", design='" + design + '\'' +
                "} " + super.toString();
    }
}
