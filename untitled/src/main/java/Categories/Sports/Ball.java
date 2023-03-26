package Categories.Sports;

import Accounts.Seller;
import Categories.Sports.Enums.BallMaterial;
import Categories.Sports.Enums.BallSize;

public class Ball extends Sports {
    private final BallSize size;
    private final BallMaterial material;
    private final boolean isRightHandOriented;

    //Constructor

    public Ball(String name, String color, int quantity, double price, Seller seller, double weight, String sportType, String brand, BallSize size, BallMaterial material, boolean isRightHandOriented) {
        super(name, color, quantity, price, seller, weight, sportType, brand);
        this.size = size;
        this.material = material;
        this.isRightHandOriented = isRightHandOriented;
    }


    //Getters and Setters

    public BallSize getSize() {
        return size;
    }

    public BallMaterial getMaterial() {
        return material;
    }

    public boolean isRightHandOriented() {
        return isRightHandOriented;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "size=" + size +
                ", material=" + material +
                ", isRightHandOriented=" + isRightHandOriented +
                "} " + super.toString();
    }
}
