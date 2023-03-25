package Categories.Clothes;

import Accounts.Seller;
import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Categories.Product;

import java.util.ArrayList;

public abstract class Clothes extends Product {

    private final ClothSize size;
    private final ClothGender gender;
    private final ClothMaterial material;
    private final String brand;
    private final ClothDurability durability;

    //Constructor

    public Clothes(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability) {
        super(name, color, quantity, price, seller, comments);
        this.size = size;
        this.gender = gender;
        this.material = material;
        this.brand = brand;
        this.durability = durability;
    }


    //Getter and Setters

    public ClothGender getGender() {
        return gender;
    }

    public ClothMaterial getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public ClothDurability getDurability() {
        return durability;
    }

    //Overrides

    @Override
    public String toString() {
        return "Clothes{" +
                "size=" + size +
                ", gender=" + gender +
                ", material=" + material +
                ", brand=" + brand +
                ", durability=" + durability +
                "} " + super.toString();
    }
}
