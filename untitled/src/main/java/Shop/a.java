package Shop;

import Accounts.Account;
import Accounts.Admin;
import Accounts.Seller;
import Accounts.User;
import Categories.Beauty.Beauty;
import Categories.Beauty.EyeBrowMakeUp;
import Categories.Beauty.EyeMakeUp;
import Categories.Books.Books;
import Categories.Books.Children_Book;
import Categories.Books.Fiction_Book;
import Categories.Books.Poetry_Book;
import Categories.Clothes.Clothes;
import Categories.Clothes.Coat;
import Categories.Clothes.Jean;
import Categories.Clothes.Sweater;
import Categories.Electronics.Electronics;
import Categories.Electronics.Laptop;
import Categories.Electronics.SmartPhone;
import Categories.Electronics.SmartWatch;
import Categories.Home.AirConditioner;
import Categories.Home.Home;
import Categories.Home.Refrigerator;
import Categories.Home.TV;
import Categories.Product;
import Categories.Sports.Ball;
import Categories.Sports.Gloves;
import Categories.Sports.Rackets;
import Categories.Sports.Sports;
import Categories.SuperMarket.Dairy;
import Categories.SuperMarket.Drinks;
import Categories.SuperMarket.Proteins;
import Categories.SuperMarket.SuperMarket;
import Categories.Tools.Drill;
import Categories.Tools.SolderingSystem;
import Categories.Tools.Spanner;
import Categories.Tools.Tools;
import Categories.ToysAndGames.BoardGames;
import Categories.ToysAndGames.CardGames;
import Categories.ToysAndGames.Puzzles;
import Categories.ToysAndGames.ToysAndGames;
import Categories.Vehicles.Car;
import Categories.Vehicles.Motorcycle;
import Categories.Vehicles.Truck;
import Categories.Vehicles.Vehicles;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import static Connection.Connect.connect;

public class a {
    //Constructor

    public Shop(String name, String webAddress, String supportPhoneNumber) {}

    public Shop(String name, String webAddress, String supportPhoneNumber, double totalGained) {}

    //Getters and Setters

    public String getName() {}

    public Product getProduct(UUID id) {}

    public HashMap<UUID, ShoppingCart> getCarts() {}

    public HashMap<UUID, Product> getProducts() {}

    public Order getOrder(UUID id) {}

    public Account getCurrentAccount() {}

    public void setCurrentAccount(Account currentAccount) {}

    public WalletReq getWalletRequest(UUID id) {}

    public User getUser(UUID id) {}

    public Seller getSeller(UUID sellerID){}


    //SignUp & Login - Related methods

    public void sellerSignUp(String companyName, String password) {}

    public void sellerSignUp(Seller newSeller) {}

    public boolean sellerLogin(String companyName, String password) {}

    public void userSignUp(String username, String password, String email, String phoneNumber, String address) {}

    public void userSignUp(User newUser) {}

    public boolean userLogin(String username, String password) {}

    public void adminSignUp(String username, String password, String email) {}

    public void adminSignUp(Admin newAdmin) {}

    public boolean adminLogin(String username, String password) {}

    //Existence - Related methods

    public boolean doesAccountExist(UUID id) {}

    public boolean doesAccountExist(String username) {}

    public boolean doesProductExist(UUID id) {}

    public boolean doesOrderExist(UUID id) {}

    //Search & Show - Related methods

    public void searchBeauty() {}

    public void searchEyeBrowMakeUp() {}

    public void searchEyeMakeUp() {}

    public void searchBooks() {}

    public void searchChildrenBook() {}

    public void searchFictionBook() {}

    public void searchPoetryBook() {}

    public void searchClothes() {}

    public void searchCoat() {}

    public void searchJean() {}

    public void searchSweater() {}

    public void searchElectronics() {}

    public void searchLaptop() {}

    public void searchSmartPhone() {}

    public void searchSmartWatch() {}

    public void searchHome() {}

    public void searchAirConditioner() {}

    public void searchRefrigerator() {}

    public void searchTV() {}

    public void searchSports() {}

    public void searchBall() {}

    public void searchGlove() {}

    public void searchRacket() {}

    public void searchSuperMarket() {}

    public void searchDairy() {}

    public void searchDrink() {}

    public void searchProtein() {}

    public void searchTools() {}

    public void searchDrill() {}

    public void searchSolderingSystem() {}

    public void searchSpanner() {}

    public void searchToysAndGames() {}

    public void searchBoardGame() {}

    public void searchCardGame() {}

    public void searchPuzzles() {}

    public void searchVehicles() {}

    public void searchCar() {}

    public void searchMotorCycle() {}

    public void searchTruck() {}

    public void showAllWalletRequests() {}

    public void showAllConfirmedWalletRequests() {}

    public void showAllUnconfirmedWalletRequests() {}

    public void showAllOrders() {}

    public void showAllConfirmedOrders() {}

    public void showAllUnconfirmedOrders() {}

    public void showAllUserWalletRequests(UUID userId) {}

    public void showUserConfirmedWalletRequests(UUID userId) {}

    public void showUserUnconfirmedWalletRequests(UUID userId) {}

    public void showUserAllOrders(UUID userId) {}

    public void showUserConfirmedOrders(UUID userId) {}

    public void showUserUnconfirmedOrders(UUID userId) {}

    public void userProfileScreens() {}

    public void userProfileScreen(UUID id) {}

    public void showUnauthorizedSellers() {}

    //Admin - Related Methods

    public void orderConfirm(UUID orderID) {}

    public void sellerAuthorization(UUID sellerID) {}

    public void walletConfirm(UUID walletID) {}

    //User - Related Methods

    public void submitComment(UUID id, String comment) {}

    public void submitAWalletRequest(WalletReq walletReq) {}

    public void submitAWalletRequestInShopOnly(WalletReq walletReq){}

    //Seller - Related Methods

    public void addProductToShop(Product product) {}

    public void addProductToShopOnly(Product product){}

    public void removeProduct(UUID productID) {}

    //Shop - Related Methods

    public void logOut() {}

    //Cart - Related Methods

    public void addOrder(Order order) {}

    public void addOrderToShopOnly(Order order){}

    public ShoppingCart getCart(UUID cartID) {}

    public void addCart(ShoppingCart cart){}

    public void addCart(String cartName){}

    //Order - Related Methods

    public void updateUserPurchasedProducts(UUID orderID) {}

    public void calcSellerCut(UUID orderID) {}

    public void addShopCut(double value) {}

    //Database - Related methods

    public void insert(){}

    public void updateShopInDatabase(){}

    public static Shop loadShopFromDatabase(){}
}
