package Categories.Sports;

import Accounts.Seller;
import Categories.Sports.Enums.FootballMaterial;
import Categories.Sports.Enums.FootballSize;

import java.util.ArrayList;

public class Football extends Sports {
    private final FootballSize size;
    private final FootballMaterial material;
    private final boolean isRightHandOriented;

    //Constructor

    public Football(String name, String color, int quantity, double price, Seller seller, double weight, String sportType, String brand, FootballSize size, FootballMaterial material, boolean isRightHandOriented) {
        super(name, color, quantity, price, seller, weight, sportType, brand);
        this.size = size;
        this.material = material;
        this.isRightHandOriented = isRightHandOriented;
    }


    //Getters and Setters

    public FootballSize getSize() {
        return size;
    }

    public FootballMaterial getMaterial() {
        return material;
    }

    public boolean isRightHandOriented() {
        return isRightHandOriented;
    }

    @Override
    public String toString() {
        return "Football{" +
                "size=" + size +
                ", material=" + material +
                ", isRightHandOriented=" + isRightHandOriented +
                "} " + super.toString();
    }
}
