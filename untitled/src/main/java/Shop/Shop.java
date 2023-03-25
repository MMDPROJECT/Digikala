package Shop;

import Accounts.Account;
import Accounts.Admin;
import Accounts.Seller;
import Accounts.User;
import Categories.Product;
import Shopping.Order;
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
    private final double totalGained;

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
    }

    //Getters and Setters

    public String getName() {
        return name;
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
                System.out.println("User has been successfully logged in!\n");
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

    public void showUserOrderRequests(UUID userID) {
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

    //User - Related Methods

    public boolean userLogin(String username, String password) {
        for (Account account : accounts.values()) {
            if (account instanceof User) {
                if (account.accountLogin(username, password)) {
                    System.out.println("User has been successfully logged in!\n");
                    return true;
                } else {
                    System.out.println("Username or password is wrong!\n");
                }
            }
        }
        return false;
    }

    public void userSignUP(String username, String password, String email, int phoneNumber, String address) {
        if (doesAccountExist(username)) {
            System.out.println("This user has already exist!\n");
        } else {
            User newUser = new User(username, password, email, phoneNumber, address);
            accounts.put(newUser.getId(), newUser);
            System.out.println("User has been successfully added!\n");
        }
    }

    public void walletRequest(double value, User user) {
        WalletReq walletRequest = new WalletReq(value, user);
        walletRequests.put(walletRequest.getId(), walletRequest);
        System.out.println("Wallet request has been successfully submitted!\n");
    }
}