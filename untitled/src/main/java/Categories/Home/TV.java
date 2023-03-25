package Categories.Home;

import Accounts.Seller;

import java.util.ArrayList;

public class TV extends Home {
    private final int refreshRate;
    private final boolean mountableOnWall;
    private final boolean has3D;
    private final boolean hasStand;

    //Constructor

    public TV(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, boolean hasController, double height, double width, double weight, int refreshRate, boolean mountableOnWall, boolean has3D, boolean hasStand) {
        super(name, color, quantity, price, seller, comments, hasController, height, width, weight);
        this.refreshRate = refreshRate;
        this.mountableOnWall = mountableOnWall;
        this.has3D = has3D;
        this.hasStand = hasStand;
    }


    //Getters and Setters

    public int getRefreshRate() {
        return refreshRate;
    }

    public boolean isMountableOnWall() {
        return mountableOnWall;
    }

    public boolean isHas3D() {
        return has3D;
    }

    public boolean isHasStand() {
        return hasStand;
    }

    //Override

    @Override
    public String toString() {
        return "TV{" +
                "refreshRate=" + refreshRate +
                ", mountableOnWall=" + mountableOnWall +
                ", has3D=" + has3D +
                ", hasStand=" + hasStand +
                "} " + super.toString();
    }
}
