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

import java.util.HashMap;
import java.util.UUID;

public class Shop {
    private final String name;
    private final String webAddress;
    private final String supportPhoneNumber;
    private final HashMap<UUID, Account> accounts;
    private final HashMap<UUID, Product> products;
    private final HashMap<UUID, Order> orders;
    private final HashMap<UUID, WalletReq> walletRequests;
    private double totalGained;
    private Account currentAccount;

    //Constructor

    public Shop(String name, String webAddress, String supportPhoneNumber) {
        this.name = name;
        this.webAddress = webAddress;
        this.supportPhoneNumber = supportPhoneNumber;
        this.totalGained = 0;
        this.accounts = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
        this.walletRequests = new HashMap<>();
        this.currentAccount = null;
    }

    //Getters and Setters

    public String getName() {
        return this.name;
    }

    public Product getProduct(UUID id) {
        if (products.containsKey(id)) {
            return products.get(id);
        } else {
            System.out.println("Product has not been found!\n");
        }
        return null;
    }

    public String getWebAddress() {
        return this.webAddress;
    }

    public String getSupportPhoneNumber() {
        return this.supportPhoneNumber;
    }

    public HashMap<UUID, Account> getAccounts() {
        return this.accounts;
    }

    public HashMap<UUID, Product> getProducts() {
        return this.products;
    }

    public HashMap<UUID, Order> getOrders() {
        return this.orders;
    }

    public Order getOrder(UUID id) {
        return this.orders.get(id);
    }

    public double getTotalGained() {
        return this.totalGained;
    }

    public HashMap<UUID, WalletReq> getWalletRequests() {
        return this.walletRequests;
    }

    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public WalletReq getWalletRequest(UUID id){
        return this.walletRequests.get(id);
    }

    public User getUser(UUID id){
        return (User) this.accounts.get(id);
    }

    //Admin - Related Methods

    public boolean doesAccountExist(UUID id) {
        return !this.accounts.containsKey(id);
    }

    public boolean doesAccountExist(String username) {
        for (Account account : accounts.values()) {
            if (account.doesAccountExist(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean adminLogin(String username, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof Admin && account.accountLogin(username, password)) {
                System.out.println("Admin has been successfully logged in!\n");
                this.currentAccount = account;
                return true;
            }
        }
        System.out.println("Username or password is wrong!\n");
        return false;
    }

    public void adminSignUp(String username, String password, String email) {
        if (!doesAccountExist(username)) {
            Admin newAdmin = new Admin(username, password, email);
            accounts.put(newAdmin.getId(), newAdmin);
            System.out.println("Admin has been successfully added!\n");
        } else {
            System.out.println("This admin already exists!\n");
        }
    }

    public void adminSignUp(Admin admin) {
        accounts.put(admin.getId(), admin);
        System.out.println("Admin has been successfully added!\n");
    }

    public void showAllWalletRequests() {
        if (this.walletRequests.size() == 0) {
            System.out.println("No wallet request has been found!\n");
        } else {
            for (WalletReq walletRequest : this.walletRequests.values()) {
                System.out.println(walletRequest);
            }
        }
    }

    public void showAllConfirmedWalletRequests() {
        boolean hasFoundAny = false;
        for (WalletReq walletReq : this.walletRequests.values()) {
            if (walletReq.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(walletReq);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No wallet request has been found!\n");
        }
    }

    public void showAllUnconfirmedWalletRequests() {
        boolean hasFoundAny = false;
        for (WalletReq walletReq : this.walletRequests.values()) {
            if (!walletReq.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(walletReq);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No wallet request has been found!\n");
        }
    }

    public void showAllOrders() {
        if (this.orders.size() == 0) {
            System.out.println("No order has been submitted yet!\n");
        } else {
            for (Order order : this.orders.values()) {
                System.out.println(order);
            }
        }
    }

    public void showAllConfirmedOrders() {
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()) {
            if (order.isConfirmed()) {
                hasFoundAny = true;
                System.out.println(order);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No order has been found!\n");
        }
    }

    public void showAllUnconfirmedOrders() {
        boolean hasFoundAny = false;
        for (Order order : this.orders.values()) {
            hasFoundAny = true;
            if (!order.isConfirmed()) {
                System.out.println(order);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No order has been found!\n");
        }
    }

    public void orderConfirm(UUID id) {
        if (!doesOrderExist(id)) {
            System.out.println("Order has not been found!\n");
        } else {
            if (((User) this.accounts.get(getOrder(id).getBuyer())).checkBuyerPocket(getOrder(id).calcBuyerPayOff())) {
                getOrder(id).orderConfirm();
                ((User) this.accounts.get(getOrder(id).getBuyer())).buyerPayOff(getOrder(id).calcBuyerPayOff());
                addShopCut(getOrder(id).calcShopCut());
                calcSellerCut(id);
                getOrder(id).updateStocks();
                updateUserPurchasedProducts(id);
            } else {
                System.out.println("Order can't be done, due to lack of money\n");
            }
        }
    }



    public void showAllUserWalletRequests(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showAllWalletRequests();
            }
        }
    }

    public void showUserConfirmedWalletRequests(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showConfirmedWalletRequests();
            }
        }
    }

    public void showUserUnconfirmedWalletRequests(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showUnconfirmedWalletRequests();
            }
        }
    }

    public void showUserAllOrders(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showAllOrders();
            }
        }
    }

    public void showUserConfirmedOrders(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showConfirmedOrders();
            }
        }
    }

    public void showUserUnconfirmedOrders(UUID userId) {
        if (doesAccountExist(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            if (this.accounts.get(userId) instanceof User) {
                ((User) this.accounts.get(userId)).showUnconfirmedOrders();
            }
        }
    }

    public void userProfileScreens() {
        boolean hasFoundAny = false;
        for (Account account : this.accounts.values()) {
            if (account instanceof User) {
                hasFoundAny = true;
                System.out.println(account);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No profile screen has been found!\n");
        }
    }

    public void userProfileScreen(UUID id) {
        if (this.accounts.get(id) instanceof User) {
            System.out.println(this.accounts.get(id));
        } else {
            System.out.println("User has not been found!\n");
        }
    }

    public void showUnauthorizedSellers() {
        boolean hasFoundAny = false;
        for (Account account : accounts.values()) {
            if (account instanceof Seller) {
                if (!((Seller) account).isAuthorized()) {
                    System.out.println(account);
                    hasFoundAny = true;
                }
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Unauthorized Seller has been found!\n");
        }
    }

    public void sellerAuthorization(UUID sellerID) {
        if (accounts.get(sellerID) instanceof Seller) {
            if (((Seller) accounts.get(sellerID)).isAuthorized()) {
                System.out.println("This Seller has been authorized!\n");
            } else {
                ((Seller) accounts.get(sellerID)).authorizeSeller();
                System.out.println("Seller has been successfully authorized!\n");
            }
        }
    }

    //User - Related Methods

    public boolean userLogin(String username, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof User) {
                if (account.accountLogin(username, password)) {
                    setCurrentAccount(account);
                    System.out.println("User has been successfully logged in!\n");
                    return true;
                } else {
                    System.out.println("Username or password is wrong!\n");
                }
            }
        }
        return false;
    }

    public void userSignUp(String username, String password, String email, String phoneNumber, String address) {
        if (this.doesAccountExist(username)) {
            System.out.println("This user already exist!\n");
        } else {
            User newUser = new User(username, password, email, phoneNumber, address);
            accounts.put(newUser.getId(), newUser);
            System.out.println("User has been successfully added!\n");
        }
    }

    public void userSignUp(User newUser) {
        this.accounts.put(newUser.getId(), newUser);
    }

    public void addPurchasedProduct(UUID id, User user) {
        user.addPurchasedProduct(products.get(id));
    }

    public void submitComment(UUID id, String comment) {
        if (this.products.containsKey(id)) {
            this.products.get(id).submitComment(comment);
            System.out.println("Comment has been successfully submitted!\n");
        } else {
            System.out.println("Product has not been found!\n");
        }
    }

    public void submitAWalletRequest(double value) {
        WalletReq newWalletReq = new WalletReq(value, this.getCurrentAccount().getId());
        ((User) this.getCurrentAccount()).submitAWalletRequest(newWalletReq);
        this.walletRequests.put(newWalletReq.getWalletID(), newWalletReq);
        System.out.println("Wallet request has been successfully submitted!\n");
    }

    public void submitAWalletRequest(WalletReq walletReq){
        this.walletRequests.put(walletReq.getWalletID(), walletReq);
        ((User)this.getCurrentAccount()).submitAWalletRequest(walletReq);
        System.out.println("Wallet request has been successfully submitted!\n");
    }

    //Seller - Related Methods

    public void sellerSignUp(String companyName, String password) {
        if (this.doesAccountExist(companyName)) {
            System.out.println("This Seller already exist!\n");
        } else {
            Seller newSeller = new Seller(companyName, password);
            this.accounts.put(newSeller.getId(), newSeller);
            System.out.println("Seller has been successfully added!\n");
        }
    }

    public void sellerSignUp(Seller seller) {
        this.accounts.put(seller.getId(), seller);
    }

    public boolean sellerLogin(String companyName, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof Seller) {
                if (account.accountLogin(companyName, password)) {
                    System.out.println("Seller has been successfully logged in!\n");
                    this.currentAccount = account;
                    return true;
                }
            }
        }
        System.out.println("Company name or password is wrong!\n");
        return false;
    }

    public void addProduct(Product product) {
        this.products.put(product.getProductID(), product);
        ((Seller) this.getCurrentAccount()).addProduct(product);
    }

    public void removeProduct(UUID id) {
        if (doesProductExist(id)) {
            this.products.remove(id);
            ((Seller) this.getCurrentAccount()).removeProduct(id);
            System.out.println("Product has been successfully removed!\n");
        } else {
            System.out.println("Product doesn't exists\n");
        }
    }

    //Shop - Related Methods

    public void logOut() {
        if (this.currentAccount == null) {
            System.out.println("Can't logout!\n");
        } else {
            this.currentAccount = null;
            System.out.println("Successfully logged out!\n");
        }
    }

    public void addShopCut(double value) {
        System.out.println("Shop's cut has been deposited!\n");
        this.totalGained += value;
    }


    //Cart - Related Methods

    public boolean doesCartExist(UUID cartID) {
        for (Account account : accounts.values()) {
            if (account instanceof User) {
                if (((User) account).getCarts().containsKey(cartID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addOrder(Order order) {
        this.orders.put(order.getOrderID(), order);
        this.getUser(order.getBuyer()).addOrder(order);
        System.out.println("Order has been successfully requested!\n");
    }

    public Order getOrderRequest(Order order){
        return order;
    }

    public boolean hasCheckout(UUID cartID){
        for (Account account : this.accounts.values()){
            if (account instanceof User){
                return ((User) account).getCart(cartID).hasCheckout();
            }
        }
        return false;
    }

    public ShoppingCart getCart(UUID cartID){
        for (Account account : this.accounts.values()){
            if (account instanceof User){
                if (((User) account).getCarts().containsKey(cartID)){
                    return ((User) account).getCart(cartID);
                }
            }
        }
        return null;
    }

    //Wallet - Related Methods

    public boolean doesWalletRequestExist(UUID id) {
        return this.walletRequests.containsKey(id);
    }

    public void walletConfirm(UUID id){
        if (this.walletRequests.containsKey(id)){
            if (!this.getWalletRequest(id).isConfirmed()){
                this.getWalletRequest(id).setConfirmed();
                this.getUser(this.getWalletRequest(id).getUser()).addWallet(this.getWalletRequest(id).getValue());
            }
            else {
                System.out.println("This wallet request has been confirmed earlier");
            }
        }
        else {
            System.out.println("Wallet request has not been found");
        }
    }

    //Order - Related Methods

    public void updateUserPurchasedProducts(UUID orderID){
        for (Product product : getOrder(orderID).getProducts()){
            if (!((User)accounts.get(getOrder(orderID).getBuyer())).isProductPurchased(product.getProductID())){
                ((User) accounts.get(getOrder(orderID).getBuyer())).addPurchasedProduct(product);
            }
        }
        System.out.println("User purchased product has been successfully updated!\n");
    }

    public boolean doesOrderExist(UUID id) {
        return this.orders.containsKey(id);
    }

    public void calcSellerCut(UUID orderID) {
        for (Product product : getOrder(orderID).getProducts()) {
            ((Seller) this.accounts.get(product.getSellerId())).addSellerCut(0.9 * product.getPrice() * getOrder(orderID).getItemNumber().get(orderID));
        }
        System.out.println("Sellers cut has been deposited!\n");
    }

    //Product - Related Methods

    public boolean doesProductExist(UUID id) {
        return products.containsKey(id);
    }

    public void searchBeauty() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Beauty) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchEyeBrowMakeUp() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof EyeBrowMakeUp) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchEyeMakeUp() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof EyeMakeUp) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchBooks() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Books) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }

    }

    public void searchChildrenBook() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Children_Book) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchFictionBook() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Fiction_Book) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchPoetryBook() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Poetry_Book) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchClothes() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Clothes) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchCoat() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Coat) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchJean() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Jean) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSweater() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Sweater) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchElectronics() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Electronics) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchLaptop() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Laptop) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSmartPhone() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof SmartPhone) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSmartWatch() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof SmartWatch) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchHome() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Home) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchAirConditioner() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof AirConditioner) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchRefrigerator() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Refrigerator) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchTV() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof TV) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSports() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Sports) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchBall() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Ball) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchGlove() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Gloves) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchRacket() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Rackets) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSuperMarket() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof SuperMarket) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchDairy() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Dairy) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchDrink() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Drinks) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchProtein() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Proteins) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchTools() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Tools) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchDrill() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Drill) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSolderingSystem() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof SolderingSystem) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchSpanner() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Spanner) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchToysAndGames() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof ToysAndGames) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchBoardGame() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof BoardGames) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchCardGame() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof CardGames) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchPuzzles() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Puzzles) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchVehicles() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Vehicles) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchCar() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Car) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchMotorCycle() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Motorcycle) {
                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }

    public void searchTruck() {
        boolean hasFoundAny = false;
        for (Product product : products.values()) {
            if (product instanceof Truck) {

                hasFoundAny = true;
                System.out.println(product);
            }
        }
        if (!hasFoundAny) {
            System.out.println("No Product has been found!\n");
        }
    }
}