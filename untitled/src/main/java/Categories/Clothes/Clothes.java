package Categories.Clothes;

import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Categories.Product;

public abstract class Clothes extends Product {

    private ClothSize size;
    private ClothGender gender;
    private ClothMaterial material;
    private String brand;
    private ClothDurability durability;

    //Constructor

    public Clothes(String name, double price, String color, int quantity, ClothSize size, ClothGender gender, ClothMaterial material, String brand, ClothDurability durability) {
        super(name, price, color, quantity);
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
