package Categories.Home;

public class TV extends Home{
    private int refreshRate;
    private boolean mountableOnWall;
    private boolean has3D;
    private boolean hasStand;

    //Constructor

    public TV(String name, double price, String color, int quantity, boolean hasController, double height, double width, double weight, int refreshRate, boolean mountableOnWall, boolean has3D, boolean hasStand) {
        super(name, price, color, quantity, hasController, height, width, weight);
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
