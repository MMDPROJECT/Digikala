package Categories;

import Categories.Beauty.EyeBrowMakeUp;
import Categories.Beauty.EyeMakeUp;
import Categories.Books.Children_Book;
import Categories.Books.Fiction_Book;
import Categories.Books.Poetry_Book;
import Categories.Clothes.Coat;
import Categories.Clothes.Jean;
import Categories.Clothes.Sweater;
import Categories.Electronics.Laptop;
import Categories.Electronics.SmartPhone;
import Categories.Electronics.SmartWatch;
import Categories.Home.AirConditioner;
import Categories.Home.Refrigerator;
import Categories.Home.TV;
import Categories.Sports.Ball;
import Categories.Sports.Gloves;
import Categories.Sports.Rackets;
import Categories.SuperMarket.Dairy;
import Categories.SuperMarket.Drinks;
import Categories.SuperMarket.Proteins;
import Categories.Tools.Drill;
import Categories.Tools.SolderingSystem;
import Categories.Tools.Spanner;
import Categories.ToysAndGames.BoardGames;
import Categories.ToysAndGames.CardGames;
import Categories.ToysAndGames.Puzzles;
import Categories.Vehicles.Car;
import Categories.Vehicles.Motorcycle;
import Categories.Vehicles.Truck;
import Shop.Shop;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import static Connection.Connect.connect;

public abstract class Product {
    private final UUID productID;
    private final UUID sellerId;
    private final String name;
    private final String color;
    private final double price;
    private int quantity;
    final ArrayList<String> comments;

    //Constructor

    public Product(String name, String color, int quantity, double price, UUID sellerId) {
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.productID = UUID.randomUUID();
        this.sellerId = sellerId;
        this.comments = new ArrayList<>();
    }

    public Product(ArrayList<String> comments, UUID productID, String name, String color, double price, UUID sellerId, int quantity) {
        this.comments = comments;
        this.productID = productID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.sellerId = sellerId;
        this.quantity = quantity;
    }

    //Getter and Setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public UUID getProductID() {
        return productID;
    }

    //Product - Related Functions

    public void submitComment(String newComment) {
        this.comments.add(newComment);
        updateProductInDatabase();
    }

    public void increaseProduct(int quantity) {
        this.quantity += quantity;
        updateProductInDatabase();
    }

    public void decreaseProduct(int quantity) {
        this.quantity -= quantity;
        updateProductInDatabase();
    }

    //Override

    @Override
    public String toString() {
        return "Product{" +
                "comments=" + comments +
                ", productID=" + productID +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", sellerId=" + sellerId +
                ", quantity=" + quantity +
                '}';
    }

    //Database - Related methods

    public void removeProductFromDatabase(){
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, productID.toString());
            stmt.executeUpdate();
//            System.out.println("Product has been successfully removed from Products table!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductInDatabase() {
        String sql = "UPDATE Products SET quantity = ?, comments = ? WHERE ProductID = ?";
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comments", comments);
            String commentsJson = jsonObject.toString();
            stmt.setInt(1, quantity);
            stmt.setString(2, commentsJson);
            stmt.setString(3, productID.toString());
            stmt.executeUpdate();
            System.out.println("Product has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadProductsFromDatabase(Shop shop){
        String sql = "SELECT * FROM Products";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                String subCategory = rs.getString("subCategory");
                //Beauty Category
                if (subCategory.equals("EyeBrowMakeUp")){
                    EyeBrowMakeUp.loadEyeBrowMakeUpFromDatabase(rs, shop);
                }
                else if (subCategory.equals("EyeMakeUp")){
                    EyeMakeUp.loadEyeMakeUpFromDatabase(rs, shop);
                }
                //Books Category
                else if (subCategory.equals("Children_Book")){
                    Children_Book.loadChildrenBookFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Fiction_Book")){
                    Fiction_Book.loadFictionBookFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Poetry_Book")){
                    Poetry_Book.loadPoetryBookFromDatabase(rs, shop);
                }
                //Clothes Category
                else if (subCategory.equals("Coat")){
                    Coat.loadCoatFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Jean")){
                    Jean.loadJeanFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Sweater")){
                    Sweater.loadSweaterFromDatabase(rs, shop);
                }
                //Electronics Category
                else if (subCategory.equals("Laptop")){
                    Laptop.loadLaptopFromDatabase(rs, shop);
                }
                else if (subCategory.equals("SmartPhone")){
                    SmartPhone.loadSmartPhoneFromDatabase(rs, shop);
                }
                else if (subCategory.equals("SmartWatch")){
                    SmartWatch.loadSmartWatchFromDatabase(rs, shop);
                }
                //Home Category
                else if (subCategory.equals("AirConditioner")){
                    AirConditioner.loadAirConditionerFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Refrigerator")){
                    Refrigerator.loadRefrigeratorFromDatabase(rs, shop);
                }
                else if (subCategory.equals("TV")){
                    TV.loadTVFromDatabase(rs, shop);
                }
                //Sports Category
                else if (subCategory.equals("Ball")){
                    Ball.loadBallFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Rackets")){
                    Rackets.loadRacketsFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Gloves")){
                    Gloves.loadGlovesFromDatabase(rs, shop);
                }
                //SuperMarket Category
                else if (subCategory.equals("Dairy")){
                    Dairy.loadDairyFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Drinks")){
                    Drinks.loadDairyFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Proteins")){
                    Proteins.loadProteinsFromDatabase(rs, shop);
                }
                //Tools Category
                else if (subCategory.equals("Drill")){
                    Drill.loadDrillFromDatabase(rs, shop);
                }
                else if (subCategory.equals("SolderingSystem")){
                    SolderingSystem.loadDrillFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Spanner")){
                    Spanner.loadSpannerFromDatabase(rs, shop);
                }
                //ToysAndGames Category
                else if (subCategory.equals("BoardGames")){
                    BoardGames.loadBoardGamesFromDatabase(rs, shop);
                }
                else if (subCategory.equals("CardGames")){
                    CardGames.loadCardGamesFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Puzzles")){
                    Puzzles.loadPuzzlesFromDatabase(rs, shop);
                }
                //Vehicles Category
                else if (subCategory.equals("Car")){
                    Car.loadCarFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Motorcycle")){
                    Motorcycle.loadCarFromDatabase(rs, shop);
                }
                else if (subCategory.equals("Truck")){
                    Truck.loadTruckFromDatabase(rs, shop);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
