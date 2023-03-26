package org.example;

import Accounts.Admin;
import Categories.*;
import Accounts.Seller;
import Accounts.User;
import Categories.Product;
import Categories.Sports.Enums.FootballMaterial;
import Categories.Sports.Enums.FootballSize;
import Categories.Sports.Football;
import Shop.Shop;
import Shopping.ShoppingCart;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Shop shop = new Shop("Digikala", "Digikala.com", "09394451013");
        Admin admin1 = new Admin("MMDPROJECT", "1382", "Bushehr, Borazjan");
        Seller seller = new Seller("Jafarestan", "1381");
        User user1 = new User("Hossein", "1381", "hossein.com", "09170861077", "Bushehr");

        Product product = new Football("Nike Premier League Academy Football 2023", "white and red-blue combined", 2, 100.5, seller, 0.5, "Football", "Nike", FootballSize.Adult, FootballMaterial.PU, true);
        seller.addProduct(product);
        shop.getAccounts().put(seller.getId(), seller);
        shop.getProducts().put(product.getId(), product);
        shop.adminSignUp(admin1);
        shop.userSignUp(user1);
        runMenu(shop);

    }

    public static void runMenu(Shop shop) {
        System.out.println("""
                ------------------------ WELCOME TO DIGIKALA ------------------------
                1- Sign Up a new Seller
                2- Sign Up a new User
                3- Login as a Seller
                4- Login as a User
                5- Login as an Admin
                6- Logout
                7- Exit
                """);
        int optionMenu = input.nextInt();
        input.nextLine();
        switch (optionMenu) {
            case 1 -> {
                //Sign up a seller
                System.out.println("Enter : 1- Company name, 2- Password\n");
                shop.sellerSignUp(input.nextLine(), input.nextLine());
                runMenu(shop);
            }
            case 2 -> {
                //Sign up a user
                System.out.println("Enter : 1- Username, 2- Password, 3- Email, 4- Phone number, 5- Address\n");
                String username = input.nextLine();
                String password = input.nextLine();
                String email = input.nextLine();
                String phoneNumber = input.nextLine();
                String address = input.nextLine();
                shop.userSignUp(username, password, email, phoneNumber, address);
                runMenu(shop);
            }
            case 3 -> {
                //Login as a seller
                System.out.println("Enter : 1- Company name, 2- Password\n");
                String companyName = input.nextLine();
                String password = input.nextLine();
                if (shop.sellerLogin(companyName, password)) {
                    sellerPage(shop);
                } else {
                    runMenu(shop);
                }
            }
            case 4 -> {
                //Login as a user
                System.out.println("Enter : 1- Username, 2- Password\n");
                String username = input.nextLine();
                String password = input.nextLine();
                if (shop.userLogin(username, password)) {
                    userPage(shop);
                } else {
                    runMenu(shop);
                }
            }
            case 5 -> {
                //Login as an admin
                System.out.println("Enter : 1- Username, 2- Password\n");
                String username = input.nextLine();
                String password = input.nextLine();
                if (shop.adminLogin(username, password)) {
                    adminPage(shop);
                } else {
                    runMenu(shop);
                }
            }
            case 6 -> {
                //Logout
                shop.logOut();
                runMenu(shop);
            }
            case 7 -> {
                //Exit
                return;
            }
        }
    }

    //        System.out.println("\n1- Search and Show Products\n2- Cart Management\n3- Wallets\n4- Submit A Comment\n5- Update Personal Information\n6- Back to Main Menu\n");
    public static void userPage(Shop shop) {
        System.out.println("------------------------------ " + shop.getCurrentAccount().getUsername().toUpperCase() + " ------------------------------");
        System.out.println("""
                1- Search And Show Products
                                
                2- Cart Management
                                
                3- Wallets
                \t- Show confirmed requests
                \t- Show unconfirmed requests
                                
                4- Submit a Comment
                                
                5- Update Personal Information
                \t- Update Password
                \t- Update Email
                \t- Update Phone number
                \t- Update Address
                                
                6- Back to Main Menu
                """);
        int optionMenu = input.nextInt();
        input.nextLine();
        switch (optionMenu) {
            case 1 -> {
                //Search and Show Products
                System.out.println("""
                                                        
                        1- Beauty
                        \t- EyeBrowMakeUp
                        \t- EyeMakeUp
                                                
                        2- Books
                        \t- Children Book
                        \t- Fiction Book
                        \t- Poetry Book
                                                
                        3- Clothes
                        \t- Coat
                        \t- Jean
                        \t- Sweater
                                                
                        4- Electronics
                        \t- Laptop
                        \t- Smart Phone
                        \t- Smart Watch
                                                
                        5- Home
                        \t- TV
                        \t- Air-conditioner
                        \t- Refrigerator
                                                
                        6- Sports
                        \t- Football
                        \t- Gloves
                        \t- Rackets
                                                
                        7- SuperMarket
                        \t- Dairy
                        \t- Drinks
                        \t- Proteins
                                                
                        8- Tools
                        \t- Drill
                        \t- Soldering System
                        \t- Spanner
                                                
                        9- Toys And Games
                        \t- Board Games
                        \t- Card Games
                        \t- Puzzles
                                                
                        10- Vehicles
                        \t- Car
                        \t- Motorcycle
                        \t- Truck
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
                        shop.searchBeauty();
                    }
                    case 2 -> {
                        shop.searchBooks();
                    }
                    case 3 -> {
                        shop.searchClothes();
                    }
                    case 4 -> {
                        shop.searchElectronics();
                    }
                    case 5 -> {
                        shop.searchHome();
                    }
                    case 6 -> {
                        shop.searchSports();
                    }
                    case 7 -> {
                        shop.searchSuperMarket();
                    }
                    case 8 -> {
                        shop.searchTools();
                    }
                    case 9 -> {
                        shop.searchToysAndGames();
                    }
                    case 10 -> {
                        shop.searchVehicles();
                    }
                }
                userPage(shop);
            }
            case 2 -> {
                //Cart Management
                System.out.println("""
                                                        
                        1- Add a new cart
                                                        
                        2- Switch cart
                                                        
                        3- Update cart
                        \t- Add product to cart by ID
                        \t- Remove product from cart by ID
                        \t- Increase count of product in cart by ID
                        \t- Decrease count of product in cart by ID
                                                        
                        4- View carts
                        \t- Show carts
                        \t- View cart by ID
                                                        
                        5- Checkout carts
                        \t- Checkout a cart by ID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
                        System.out.println("Enter name of the cart\n");
                        String cartName = input.nextLine();
                        ShoppingCart cart = new ShoppingCart(cartName);
                        if (shop.getCurrentAccount() instanceof User) {
                            ((User) shop.getCurrentAccount()).addCart(cart);
                        }
                        System.out.println("Cart has been successfully added!\n");
                    }
                    case 2 -> {
                        System.out.println("Enter : - The ID of the cart you want to switch to\n");
                        String id = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User) {
                            ((User) shop.getCurrentAccount()).setCurrentCart(UUID.fromString(id));
                        }
                    }
                    case 3 -> {
                        System.out.println("""
                                1- Add product to cart by ID
                                2- Remove product from cart by ID
                                3- Increase count of product in cart by ID
                                4- Decrease count of product in cart by ID
                                """);
                        optionMenu = input.nextInt();
                        input.nextLine();
                        switch (optionMenu) {
                            case 1 -> {
                                System.out.println("Enter : 1- Product ID, 2- Quantity you want to buy\n");
                                String id = input.nextLine();
                                int quantity = input.nextInt();
                                input.nextLine();
                                if (shop.getCurrentAccount() instanceof User) {
                                    ((User) shop.getCurrentAccount()).getCurrentCart().addProduct(shop.getProduct(UUID.fromString(id)), quantity);
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter : - Product ID\n");
                                String id = input.nextLine();
                                if (shop.getCurrentAccount() instanceof User) {
                                    if (((User) shop.getCurrentAccount()).getCurrentCart().doesProductExist(UUID.fromString(id))) {
                                        ((User) shop.getCurrentAccount()).getCurrentCart().removeProduct(UUID.fromString(id));
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter : 1- Product ID, 2- Quantity to add\n");
                                String id = input.nextLine();
                                int quantity = input.nextInt();
                                input.nextLine();
                                if (shop.getCurrentAccount() instanceof User) {
                                    if (((User) shop.getCurrentAccount()).getCurrentCart().doesProductExist(UUID.fromString(id))) {
                                        ((User) shop.getCurrentAccount()).getCurrentCart().increaseAmount(UUID.fromString(id), quantity);
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                            }
                            case 4 -> {
                                System.out.println("Enter : 1- Product ID, 2- Quantity to decrease\n");
                                String id = input.nextLine();
                                int quantity = input.nextInt();
                                input.nextLine();
                                if (shop.getCurrentAccount() instanceof User) {
                                    if (((User) shop.getCurrentAccount()).getCurrentCart().doesProductExist(UUID.fromString(id))) {
                                        ((User) shop.getCurrentAccount()).getCurrentCart().increaseAmount(UUID.fromString(id), quantity);
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("""
                                1- Show all carts
                                2- View cart by ID
                                """);
                        optionMenu = input.nextInt();
                        input.nextLine();
                        switch (optionMenu) {
                            case 1 -> {
                                if (shop.getCurrentAccount() instanceof User) {
                                    ((User) shop.getCurrentAccount()).viewCarts();
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter : Cart ID\n");
                                String id = input.nextLine();
                                if (shop.getCurrentAccount() instanceof User) {
                                    System.out.println(((User) shop.getCurrentAccount()).getCart(UUID.fromString(id)));
                                }
                            }
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter : - Cart ID\n");
                        String id = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User) {
                            ((User) shop.getCurrentAccount()).checkOutCart(UUID.fromString(id));
                        }
                    }

                }
            }
            case 3 -> {
                //Wallets
                System.out.println("""
                        1- Wallet request
                                                           
                        2- Show wallet requests
                        \t- Show confirmed requests
                        \t- Show unconfirmed requests
                                                           
                        3- Show wallet
                        \t- Show wallet
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
                        System.out.println("Enter : - Wallet value\n");
                        double value = input.nextDouble();
                        input.nextLine();
                        if (shop.getCurrentAccount() instanceof User) {
                            ((User) shop.getCurrentAccount()).sendWalletRequest(value);
                        }
                    }
                    case 2 -> {
                        System.out.println("""
                                1- Show confirmed requests
                                2- Show unconfirmed requests
                                """);
                        optionMenu = input.nextInt();
                        input.nextLine();
                        switch (optionMenu) {
                            case 1 -> {
                                if (shop.getCurrentAccount() instanceof User) {
                                    ((User) shop.getCurrentAccount()).showConfirmedWalletRequests();
                                }
                            }
                            case 2 -> {
                                if (shop.getCurrentAccount() instanceof User) {
                                    ((User) shop.getCurrentAccount()).showUnconfirmedWalletRequests();
                                }
                            }
                        }
                    }
                    case 3 -> {
                        if (shop.getCurrentAccount() instanceof User) {
                            System.out.println("Current wallet : " + ((User) shop.getCurrentAccount()).getWallet());
                        }
                    }
                }
            }
            case 4 -> {
                //Submit A Comment
                System.out.println("Enter : 1- Product ID, 2- Comment\n");
                String id = input.nextLine();
                String comment = input.nextLine();
                shop.submitComment(UUID.fromString(id), comment);
            }
            case 5 -> {
                //Update Personal Information
                System.out.println("""
                        1- Update Password
                        2- Update Email
                        3- Update Phone number
                        4- Update Address
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu){
                    case 1 -> {
                        System.out.println("Enter : - New password\n");
                        String newPassword = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User){
                            ((User) shop.getCurrentAccount()).updatePassword(newPassword);
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter : - New email\n");
                        String newEmail = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User){
                            ((User) shop.getCurrentAccount()).updateEmail(newEmail);
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter : - New phone number\n");
                        String newPhoneNumber = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User){
                            ((User) shop.getCurrentAccount()).updatePhoneNumber(newPhoneNumber);
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter : - New address\n");
                        String newAddress = input.nextLine();
                        if (shop.getCurrentAccount() instanceof User){
                            ((User) shop.getCurrentAccount()).updateAddress(newAddress);
                        }
                    }
                }

            }
            case 6 -> {
                //Back to Main Menu
                runMenu(shop);
                return;
            }
        }
        userPage(shop);
    }

    public static void sellerPage(Shop shop) {
        //TODO
    }

    public static void adminPage(Shop shop) {
        //TODO
    }
}