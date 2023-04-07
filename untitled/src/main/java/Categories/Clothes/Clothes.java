package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Categories.Product;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Clothes extends Product {

    private final ClothSize size;
    private final ClothGender gender;
    private final ClothMaterial material;
    private final String brand;
    private final ClothDurability durability;

    //Constructor

    public Clothes(String name, String color, int quantity, double price, UUID sellerID, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability) {
        super(name, color, quantity, price, sellerID);
        this.size = size;
        this.gender = gender;
        this.material = material;
        this.brand = brand;
        this.durability = durability;
    }

    public Clothes(ArrayList<String> comments, UUID id, String name, String color, double price, UUID sellerId, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability) {
        super(comments, id, name, color, price, sellerId, quantity);
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

    public ClothSize getSize() {
        return size;
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
