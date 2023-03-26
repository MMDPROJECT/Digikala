package Shop;

import Accounts.Account;
import Accounts.Admin;
import Accounts.Seller;
import Accounts.User;
import Categories.Beauty.Beauty;
import Categories.Books.Books;
import Categories.Clothes.Clothes;
import Categories.Electronics.Electronics;
import Categories.Home.Home;
import Categories.Product;
import Categories.Sports.Sports;
import Categories.SuperMarket.SuperMarket;
import Categories.Tools.Tools;
import Categories.ToysAndGames.ToysAndGames;
import Categories.Vehicles.Vehicles;
import Shopping.Order;
import Shopping.WalletReq;

import java.util.HashMap;
import java.util.UUID;

public class Shop {
    private String name;
    private String webAddress;
    private String supportPhoneNumber;
    private HashMap<UUID, Account> accounts;
    private HashMap<UUID, Product> products;
    private HashMap<UUID, Order> orders;
    private HashMap<UUID, WalletReq> walletRequests;
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
        return name;
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
        return webAddress;
    }

    public String getSupportPhoneNumber() {
        return supportPhoneNumber;
    }

    public HashMap<UUID, Account> getAccounts() {
        return accounts;
    }

    public HashMap<UUID, Product> getProducts() {
        return products;
    }

    public HashMap<UUID, Order> getOrders() {
        return orders;
    }

    public double getTotalGained() {
        return totalGained;
    }

    public HashMap<UUID, WalletReq> getWalletRequests() {
        return walletRequests;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    //Admin - Related Methods

    public boolean doesAccountExist(UUID id) {
        return accounts.containsKey(id);
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
        if (walletRequests.size() == 0) {
            System.out.println("No wallet request has been found!\n");
        } else {
            for (WalletReq walletRequest : walletRequests.values()) {
                System.out.println(walletRequest);
            }
        }
    }

    public void showUserWalletRequests(UUID userId) {
        if (!accounts.containsKey(userId)) {
            System.out.println("User has not been found!\n");
        } else {
            for (UUID uuid : accounts.keySet()) {
                if (uuid.equals(userId)) {
                    if (accounts.get(userId) instanceof User) {
                        ((User) accounts.get(userId)).showWalletRequests();
                    }
                }
            }
        }
    }

    public void walletConfirm(UUID id) {
        if (!walletRequests.containsKey(id)) {
            System.out.println("Wallet request has not been found!\n");
        } else {
            if (!walletRequests.get(id).isConfirmed()) {
                walletRequests.get(id).walletConfirm();
                System.out.println("Wallet request has been successfully confirmed!\n");
            } else {
                System.out.println("This wallet request has been confirmed earlier!\n");
            }
        }
    }

    public void showAllOrderRequests() {
        if (orders.size() == 0) {
            System.out.println("No order has been submitted yet!\n");
        } else {
            for (Order order : orders.values()) {
                System.out.println(order);
            }
        }
    }

    public void showUserOrderRequests() {
        UUID userID = this.currentAccount.getId();
        if (accounts.get(userID) instanceof User) {
            if (((User) accounts.get(userID)).getOrders().size() == 0) {
                System.out.println("No order has been submitted for this user yet!\n");
            } else {
                for (Order order : ((User) accounts.get(userID)).getOrders().values()) {
                    System.out.println(order);
                }
            }
        }
    }

    public void orderConfirm(UUID id) {
        if (!orders.containsKey(id)) {
            System.out.println("Order has not been found!\n");
        } else {
            if (orders.get(id).isConfirmed()) {
                System.out.println("This order has been confirmed earlier!\n");
            } else {
                orders.get(id).orderConfirm();
                System.out.println("Order has been successfully confirmed");
            }
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
            }
        }
    }

    public void userProfileScreens() {
        boolean hasFoundAny = false;
        for (Account account : accounts.values()) {
            if (account instanceof User) {
                System.out.println(account);
            }
        }
    }

    public void userProfileScreen(UUID id) {
        if (accounts.get(id) instanceof User) {
            System.out.println(accounts.get(id));
        } else {
            System.out.println("User has not been found!\n");
        }
    }

    //User - Related Methods

    public boolean userLogin(String username, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof User) {
                if (account.accountLogin(username, password)) {
                    System.out.println("User has been successfully logged in!\n");
                    this.currentAccount = account;
                    return true;
                } else {
                    System.out.println("Username or password is wrong!\n");
                }
            }
        }
        return false;
    }

    public void userSignUp(String username, String password, String email, String phoneNumber, String address) {
        if (doesAccountExist(username)) {
            System.out.println("This user has already exist!\n");
        } else {
            User newUser = new User(username, password, email, phoneNumber, address);
            accounts.put(newUser.getId(), newUser);
            System.out.println("User has been successfully added!\n");
        }
    }

    public void userSignUp(User newUser){
        this.accounts.put(newUser.getId(), newUser);
    }

    public void walletRequest(double value, User user) {
        WalletReq walletRequest = new WalletReq(value, user);
        walletRequests.put(walletRequest.getId(), walletRequest);
        System.out.println("Wallet request has been successfully submitted!\n");
    }

    public void addPurchasedProduct(UUID id, User user) {
        user.addPurchasedProduct(products.get(id));
    }

    public void submitComment (UUID id, String comment){
        if (this.products.containsKey(id)){
            this.products.get(id).submitComment(comment);
        }
        else {
            System.out.println("Product has not been found!\n");
        }
    }

    //Seller - Related Methods

    public void sellerSignUp(String companyName, String password) {
        if (doesAccountExist(companyName)) {
            System.out.println("This Seller has already exist!\n");
        } else {
            Seller newSeller = new Seller(companyName, password);
            this.accounts.put(newSeller.getId(), newSeller);
            System.out.println("Seller has been successfully added!\n");
        }
    }

    public void sellerSignUp(Seller seller){
        this.accounts.put(seller.getId(), seller);
    }

    public boolean sellerLogin(String companyName, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof Seller) {
                if (account.accountLogin(companyName, password)) {
                    System.out.println("Seller has been successfully logged in!\n");
                    this.currentAccount = account;
                    return true;
                } else {
                    System.out.println("Company name or password is wrong!\n");
                }
            }
        }
        return false;
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

    //Cart - Related Methods

    //Product - Related Methods

    public boolean doesProductExist(UUID id){
        return products.containsKey(id);
    }
}