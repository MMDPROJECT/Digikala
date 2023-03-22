package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;

public class Jean extends Clothes{
    private double height;
    private int pocketNumber;
    private boolean hasZipper;

    //Constructor

    public Jean(String name, double price, String color, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability, double height, int pocketNumber, boolean hasZipper) {
        super(name, price, color, quantity, size, gender, material, brand, durability);
        this.height = height;
        this.pocketNumber = pocketNumber;
        this.hasZipper = hasZipper;
    }


    //Getter and Setters

    public double getHeight() {
        return height;
    }

    public int getPocketNumber() {
        return pocketNumber;
    }

    public boolean isHasZipper() {
        return hasZipper;
    }

    //Override

    @Override
    public String toString() {
        return "Jean{" +
                "height=" + height +
                ", pocketNumber=" + pocketNumber +
                ", hasZipper=" + hasZipper +
                "} " + super.toString();
    }
}
