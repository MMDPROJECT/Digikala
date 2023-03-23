package Categories.Sports;

import Categories.Sports.Enums.FootballMaterial;
import Categories.Sports.Enums.FootballSize;

public class Football extends Sports{
    private FootballSize size;
    private FootballMaterial material;
    private boolean isRightHandOriented;

    //Constructor

    public Football(String name, double price, String color, int quantity, double weight, String sportType, String brand, FootballSize size, FootballMaterial material, boolean isRightHandOriented) {
        super(name, price, color, quantity, weight, sportType, brand);
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
