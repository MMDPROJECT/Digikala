package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

public class Sweater extends Clothes{
    private int buttonNumber;

    //Constructor

    public Sweater(String name, double price, String color, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, int buttonNumber) {
        super(name, price, color, quantity, size, gender, material, brand, durability);
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
