import Accounts.Admin;
import Accounts.Seller;
import Accounts.User;
import Categories.Beauty.Enums.MatterState;
import Categories.Beauty.Enums.PenType;
import Categories.Beauty.EyeBrowMakeUp;
import Categories.Books.Children_Book;
import Categories.Books.Fiction_Book;
import Categories.Books.Poetry_Book;
import Categories.Clothes.Coat;
import Categories.Clothes.Enums.ClothDurability;
import Categories.Clothes.Enums.ClothGender;
import Categories.Clothes.Enums.ClothMaterial;
import Categories.Clothes.Enums.ClothSize;
import Categories.Clothes.Jean;
import Categories.Clothes.Sweater;
import Categories.Electronics.Laptop;
import Categories.Electronics.SmartPhone;
import Categories.Electronics.SmartWatch;
import Categories.Home.AirConditioner;
import Categories.Home.Enums.RefrigeratorType;
import Categories.Home.Refrigerator;
import Categories.Home.TV;
import Categories.Product;
import Categories.Sports.Ball;
import Categories.Sports.Enums.*;
import Categories.Sports.Gloves;
import Categories.Sports.Rackets;
import Categories.SuperMarket.Dairy;
import Categories.SuperMarket.Drinks;
import Categories.SuperMarket.Enums.DairyGroups;
import Categories.SuperMarket.Enums.DrinkSize;
import Categories.SuperMarket.Enums.ProteinProductType;
import Categories.SuperMarket.Proteins;
import Categories.Tools.Drill;
import Categories.Tools.Enums.PowerSource;
import Categories.Tools.Enums.SpannerMaterial;
import Categories.Tools.Enums.UsageLevel;
import Categories.Tools.SolderingSystem;
import Categories.Tools.Spanner;
import Categories.ToysAndGames.BoardGames;
import Categories.ToysAndGames.CardGames;
import Categories.ToysAndGames.Enums.DifficultyLevel;
import Categories.ToysAndGames.Puzzles;
import Categories.Vehicles.Car;
import Categories.Vehicles.Enums.NoiseLevel;
import Categories.Vehicles.Enums.TruckType;
import Categories.Vehicles.Motorcycle;
import Categories.Vehicles.Truck;
import Shop.Shop;
import Shopping.Order;
import Shopping.ShoppingCart;
import Shopping.WalletReq;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Shop shop = Shop.loadShopFromDatabase();
        Product.loadProductsFromDatabase(shop);
        WalletReq.loadWalletRequestsFromDatabase(shop);
        Admin.loadAdminsFromDatabase(shop);
        Seller.loadSellersFromDatabase(shop);
        ShoppingCart.loadShoppingCartsFromDatabase(shop);
        Order.loadOrdersFromDatabase(shop);
        User.loadUsersFromDatabase(shop);
        //Opening main menu...
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
                6- Exit the program
                """);
        int optionMenu = input.nextInt();
        input.nextLine();
        switch (optionMenu) {
            case 1 -> {
                //Sign up a seller
                System.out.println("Enter : 1- Company name, 2- Password\n");
                String companyName = input.nextLine();
                String password = input.nextLine();
                shop.sellerSignUp(companyName, password);
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
                //Exit the program
                return;
            }
            default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
        }
    }

    public static void userPage(Shop shop) {
        System.out.println("------------------------------ " + shop.getCurrentAccount().getUsername().toUpperCase() + " ------------------------------");
        System.out.println("""
                1- Search And Show Products
                \t- Search through categories
                \t- Show purchased products
                                
                2- Cart Management
                \t- Add a new cart
                \t- Switch cart
                \t- Update cart
                \t- View carts
                \t- Checkout carts
                             
                3- Wallets
                \t- Submit a wallet request
                \t- Show wallet requests
                \t- Show wallet
                                
                4- Submit a Comment
                                
                5- Update Personal Information
                \t- Update Password
                \t- Update Email
                \t- Update Phone number
                \t- Update Address
                
                6- Logout
                                
                7- Back to Main Menu
                """);
        int optionMenu = input.nextInt();
        input.nextLine();
        User currentUser = (User) shop.getCurrentAccount();
        switch (optionMenu) {
            case 1 -> {
                //Search and Show Products
                System.out.println("""
                        1- Search through categories
                        2- Show purchased products
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
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
                                \t- Ball
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
                                System.out.println("""
                                        1- Search through whole Beauty Products
                                        2- Search through Eye Brow MakeUp
                                        3- Search through Eye MakeUp
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchBeauty();
                                    case 2 -> shop.searchEyeBrowMakeUp();
                                    case 3 -> shop.searchEyeMakeUp();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 2 -> {
                                System.out.println("""
                                        1- Search through whole Book Products
                                        2- Search through Children Books
                                        3- Search through Fiction Books
                                        4- Search through Poetry Books
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchBooks();
                                    case 2 -> shop.searchChildrenBook();
                                    case 3 -> shop.searchFictionBook();
                                    case 4 -> shop.searchPoetryBook();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 3 -> {
                                System.out.println("""
                                        1- Search through whole Clothes Products
                                        2- Search through Coats
                                        3- Search through Jeans
                                        4- Search through Sweaters
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchClothes();
                                    case 2 -> shop.searchCoat();
                                    case 3 -> shop.searchJean();
                                    case 4 -> shop.searchSweater();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 4 -> {
                                System.out.println("""
                                        1- Search through whole Electronic Products
                                        2- Search through Laptops
                                        3- Search through Smart Phones
                                        4- Search through Smart Watches
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchElectronics();
                                    case 2 -> shop.searchLaptop();
                                    case 3 -> shop.searchSmartPhone();
                                    case 4 -> shop.searchSmartWatch();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 5 -> {
                                System.out.println("""
                                        1- Search through whole Home Products
                                        2- Search through Air Conditioners
                                        3- Search through Refrigerators
                                        4- Search through TVs
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchHome();
                                    case 2 -> shop.searchAirConditioner();
                                    case 3 -> shop.searchRefrigerator();
                                    case 4 -> shop.searchTV();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 6 -> {
                                System.out.println("""
                                        1- Search through whole Sport Products
                                        2- Search through Balls
                                        3- Search through Gloves
                                        4- Search through Rackets
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchSports();
                                    case 2 -> shop.searchBall();
                                    case 3 -> shop.searchGlove();
                                    case 4 -> shop.searchRacket();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 7 -> {
                                System.out.println("""
                                        1- Search through whole Super Market Products
                                        2- Search through Dairies
                                        3- Search through Drinks
                                        4- Search through Proteins
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchSuperMarket();
                                    case 2 -> shop.searchDairy();
                                    case 3 -> shop.searchDrink();
                                    case 4 -> shop.searchProtein();

                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 8 -> {
                                System.out.println("""
                                        1- Search through whole Tools Products
                                        2- Search through Drills
                                        3- Search through Soldering Systems
                                        4- Search through Spanners
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchTools();
                                    case 2 -> shop.searchDrill();
                                    case 3 -> shop.searchSolderingSystem();
                                    case 4 -> shop.searchSpanner();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 9 -> {
                                System.out.println("""
                                        1- Search through whole Toy and Game Products
                                        2- Search through Board Games
                                        3- Search through Card Games
                                        4- Search through Puzzles
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchToysAndGames();
                                    case 2 -> shop.searchBoardGame();
                                    case 3 -> shop.searchCardGame();
                                    case 4 -> shop.searchPuzzles();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            case 10 -> {
                                System.out.println("""
                                        1- Search through whole Vehicle Products
                                        2- Search through Cars
                                        3- Search through Motorcycles
                                        4- Search through Trucks
                                        """);
                                optionMenu = input.nextInt();
                                input.nextLine();
                                switch (optionMenu) {
                                    case 1 -> shop.searchVehicles();
                                    case 2 -> shop.searchCar();
                                    case 3 -> shop.searchMotorCycle();
                                    case 4 -> shop.searchTruck();
                                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                                }
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                        }
                    }
                    case 2 -> currentUser.showPurchasedProducts();
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
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
                        \t- View cart details by ID
                        \t- Show all orders
                        \t- Show confirmed orders
                        \t- Show unconfirmed orders
                                                        
                        5- Checkout carts
                        \t- Checkout a cart by ID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
                        System.out.println("Enter : - Name of the cart\n");
                        String cartName = input.nextLine();
                        shop.addCart(cartName);
                    }
                    case 2 -> {
                        System.out.println("Enter : - The ID of the cart you want to switch to\n");
                        String productID = input.nextLine();
                        currentUser.setCurrentCart(UUID.fromString(productID));
                    }
                    case 3 -> {
                        if (currentUser.hasSelectedCart()) {
                            System.out.println("""
                                    1- Add product to cart by ID
                                    2- Remove product from cart by ID
                                    3- Increase count of product in cart by ID
                                    4- Decrease count of product in cart by ID
                                    """);
                            optionMenu = input.nextInt();
                            input.nextLine();
                            ShoppingCart currentCart = shop.getCart(currentUser.getCurrentCartID());
                            switch (optionMenu) {
                                case 1 -> {
                                    System.out.println("Enter : 1- Product ID, 2- Quantity you want to buy\n");
                                    String productID = input.nextLine();
                                    int quantity = input.nextInt();
                                    input.nextLine();
                                    if (shop.doesProductExist(UUID.fromString(productID))) {
                                        Product currentProduct = shop.getProduct(UUID.fromString(productID));
                                        currentCart.addProductToCart(currentProduct, quantity);
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Enter : - Product ID\n");
                                    String productID = input.nextLine();
                                    if (currentCart.doesProductExist(UUID.fromString(productID))) {
                                        currentCart.removeProduct(UUID.fromString(productID));
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Enter : 1- Product ID, 2- Quantity to add\n");
                                    String productID = input.nextLine();
                                    int quantity = input.nextInt();
                                    input.nextLine();
                                    if (currentCart.doesProductExist(UUID.fromString(productID))) {
                                        currentCart.increaseQuantityInCart(UUID.fromString(productID), quantity);
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Enter : 1- Product ID, 2- Quantity to decrease\n");
                                    String productID = input.nextLine();
                                    int quantity = input.nextInt();
                                    input.nextLine();
                                    if (currentCart.doesProductExist(UUID.fromString(productID))) {
                                        currentCart.decreaseQuantityInCart(UUID.fromString(productID), quantity);
                                    } else {
                                        System.out.println("Product has not been found!\n");
                                    }
                                }
                            }
                        } else {
                            System.out.println("No cart has been selected!\n");
                        }
                    }
                    case 4 -> {
                        System.out.println("""
                                1- Show all carts
                                2- View cart details by ID
                                3- Show all orders
                                4- Show confirmed orders
                                5- Show unconfirmed orders
                                """);
                        optionMenu = input.nextInt();
                        input.nextLine();
                        switch (optionMenu) {
                            case 1 -> currentUser.viewCarts();
                            case 2 -> {
                                System.out.println("Enter : - Cart ID\n");
                                String cartID = input.nextLine();
                                currentUser.viewCart(UUID.fromString(cartID));
                            }
                            case 3 -> ((User) shop.getCurrentAccount()).showAllOrders();
                            case 4 -> ((User) shop.getCurrentAccount()).showConfirmedOrders();
                            case 5 -> ((User) shop.getCurrentAccount()).showUnconfirmedOrders();
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter : - Cart ID\n");
                        String cartID = input.nextLine();
                        ShoppingCart cart = shop.getCart(UUID.fromString(cartID));
                        if (!cart.hasCheckout()) {
                            cart.checkoutCart();
                            Order newOrder = new Order(cart.getName(), cart.getProducts(), cart.getItemNumber(), cart.getTotalPrice(), shop.getCurrentAccount().getAccountID());        //Will be added to the Orders table in the database automatically
                            shop.addOrder(newOrder);        //Will be added to User orders column in the database automatically
                        } else {
                            System.out.println("Cart has been checkout earlier!\n");
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 3 -> {
                //Wallets
                System.out.println("""
                        1- Submit a Wallet request
                                                           
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
                        WalletReq newWalletReq = new WalletReq(value, currentUser.getAccountID());      //Will be added to the WalletRequest table in the database automatically
                        shop.submitAWalletRequest(newWalletReq);        //Will be added to the WalletRequests column of the user automatically
                    }
                    case 2 -> {
                        System.out.println("""
                                1- Show confirmed requests
                                2- Show unconfirmed requests
                                """);
                        optionMenu = input.nextInt();
                        input.nextLine();
                        switch (optionMenu) {
                            case 1 -> currentUser.showConfirmedWalletRequests();
                            case 2 -> currentUser.showUnconfirmedWalletRequests();
                            default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                        }
                    }
                    case 3 -> currentUser.viewWallet();
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 4 -> {
                //Submit A Comment
                System.out.println("Enter : 1- Product ID, 2- Comment\n");
                String productID = input.nextLine();
                String comment = input.nextLine();
                shop.submitComment(UUID.fromString(productID), comment);
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
                switch (optionMenu) {
                    case 1 -> {
                        System.out.println("Enter : - New password\n");
                        String newPassword = input.nextLine();
                        currentUser.updatePassword(newPassword);
                    }
                    case 2 -> {
                        System.out.println("Enter : - New email\n");
                        String newEmail = input.nextLine();
                        currentUser.updateEmail(newEmail);
                    }
                    case 3 -> {
                        System.out.println("Enter : - New phone number\n");
                        String newPhoneNumber = input.nextLine();
                        currentUser.updatePhoneNumber(newPhoneNumber);
                    }
                    case 4 -> {
                        System.out.println("Enter : - New email\n");
                        String newAddress = input.nextLine();
                        currentUser.updateAddress(newAddress);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 6 -> {
                //Logout
                shop.logOut();
                runMenu(shop);
            }
            case 7 -> {
                //Back to Main Menu
                runMenu(shop);
                return;
            }
            default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
        }
        userPage(shop);
    }

    public static void sellerPage(Shop shop) {
        System.out.println("------------------------------ " + shop.getCurrentAccount().getUsername().toUpperCase() + " ------------------------------");
        if (!((Seller) shop.getCurrentAccount()).isAuthorized()) {
            System.out.println("\n\tSeller has not been authorized yet!\n");
            runMenu(shop);
        } else {
            Seller currentSeller = (Seller) shop.getCurrentAccount();
            System.out.println("""
                    1- Product Management
                    \t- Add a new Product
                    \t- Remove a Product By ID
                    \t- Show all Available Products
                                               
                    2- View Wallet
                    
                    3- Logout
                                               
                    4- Back to Main Menu
                    """);
            int optionMenu = input.nextInt();
            input.nextLine();
            switch (optionMenu) {
                case 1 -> {
                    //Product Management
                    System.out.println("""
                            1- Add a new Product
                            2- Remove a Product by ID
                            3- Show all Available Products
                            """);
                    optionMenu = input.nextInt();
                    input.nextLine();
                    switch (optionMenu) {
                        case 1 -> {
                            System.out.println("""
                                    Select a category from below:
                                    1- Beauty
                                    2- Books
                                    3- Clothes
                                    4- Electronics
                                    5- Home
                                    6- Sports
                                    7- SuperMarket
                                    8- Tools
                                    9- Toys And Games
                                    10- Vehicles
                                    """);
                            optionMenu = input.nextInt();
                            input.nextLine();
                            System.out.println("""
                                    (In each part you will be asked about some information, please answer them correctly)
                                    Enter:
                                    1- Name
                                    2- Color
                                    3- Quantity
                                    4- Price
                                    """);
                            String name = input.nextLine();
                            String color = input.nextLine();
                            int quantity = input.nextInt();
                            input.nextLine();
                            double price = input.nextDouble();
                            input.nextLine();
                            switch (optionMenu) {
                                case 1 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Eye Brow Makeup
                                            2- Eye Makeup
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Material State
                                            \t-Solid, Powder, Liquid
                                            6- Does it have box?
                                            \t-true, false
                                            """);
                                    String matterState = input.nextLine();
                                    String hasBox = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    7- Type of Pen
                                                    \t- Eyeliner, Mascara, Eyeshadow
                                                    8- Does it have Water Resistance?
                                                    \t- true, false
                                                    9- Brand
                                                    10-Longevity(per hour)
                                                    """);
                                            String penType = input.nextLine();
                                            String hasWaterResistance = input.nextLine();
                                            String brand = input.nextLine();
                                            int longevity = input.nextInt();
                                            input.nextLine();
                                            EyeBrowMakeUp eyeBrowMakeUp = new EyeBrowMakeUp(name, color, quantity, price, currentSeller.getAccountID(), MatterState.valueOf(matterState.toUpperCase()), Boolean.parseBoolean(hasBox), PenType.valueOf(penType.toUpperCase()), Boolean.parseBoolean(hasWaterResistance), brand, longevity);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(eyeBrowMakeUp);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    7- Type of Pen
                                                    \t- Eyeliner, Mascara, Eyeshadow
                                                    8- Does it have Water Resistance?
                                                    \t- true, false
                                                    9- Brand
                                                    10- longevity
                                                    """);
                                            String penType = input.nextLine();
                                            String hasWaterResistance = input.nextLine();
                                            String brand = input.nextLine();
                                            int longevity = input.nextInt();
                                            input.nextLine();
                                            EyeBrowMakeUp eyeMakeUp = new EyeBrowMakeUp(name, color, quantity, price, currentSeller.getAccountID(), MatterState.valueOf(matterState.toUpperCase()), Boolean.parseBoolean(hasBox), PenType.valueOf(penType.toUpperCase()), Boolean.parseBoolean(hasWaterResistance), brand, longevity);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(eyeMakeUp);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 2 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Fiction book
                                            2- Children book
                                            3- Poetry book
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- ISBN
                                            6- Page Numbers
                                            7- Author
                                            8- Language
                                            """);
                                    String ISBN = input.nextLine();
                                    int pageNumber = input.nextInt();
                                    input.nextLine();
                                    String author = input.nextLine();
                                    String language = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    9- Tone
                                                    """);
                                            String tone = input.nextLine();
                                            System.out.println("10- Enter characters one after the other and enter -1 when are you finished");
                                            ArrayList<String> characters = new ArrayList<>();
                                            while (true) {
                                                String character = input.nextLine();
                                                if (!character.equals("-1")) {
                                                    characters.add(character);
                                                } else {
                                                    break;
                                                }
                                            }
                                            Fiction_Book fiction_book = new Fiction_Book(name, color, quantity, price, currentSeller.getAccountID(), ISBN, pageNumber, author, language, characters, tone);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(fiction_book);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    9- Reading Level(for example: 8-12 yrs olds)
                                                    10- Theme
                                                    """);
                                            String readingLevel = input.nextLine();
                                            String theme = input.nextLine();
                                            Children_Book children_book = new Children_Book(name, color, quantity, price, currentSeller.getAccountID(), ISBN, pageNumber, author, language, readingLevel, theme);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(children_book);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    9- Poetic Form
                                                    10- Count of Verses
                                                    """);
                                            String poeticForm = input.nextLine();
                                            int verseNumber = input.nextInt();
                                            input.nextLine();
                                            Poetry_Book poetry_book = new Poetry_Book(name, color, quantity, price, currentSeller.getAccountID(), ISBN, pageNumber, author, language, poeticForm, verseNumber);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(poetry_book);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 3 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Coat
                                            2- Jean
                                            3- Sweater
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Size
                                            \t- Small, Medium, Large, XLarge
                                            6- Cloth's Gender
                                            \t- Male, Female, Unisex
                                            7- Cloth's Material
                                            \t- Polyester, Cotton, Wool, Other
                                            8- Brand
                                            9- Durability
                                            \t- High, Medium, Low
                                            """);
                                    String size = input.nextLine();
                                    String clothGender = input.nextLine();
                                    String clothMaterial = input.nextLine();
                                    String brand = input.nextLine();
                                    String durability = input.nextLine();

                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Button Number
                                                    11- Does it have Cap?
                                                    \t- true, false
                                                    """);
                                            int buttonNumber = input.nextInt();
                                            input.nextLine();
                                            String hasCap = input.nextLine();
                                            Coat coat = new Coat(name, color, quantity, price, currentSeller.getAccountID(), ClothSize.valueOf(size.toUpperCase()), ClothGender.valueOf(clothGender.toUpperCase()), ClothMaterial.valueOf(clothMaterial.toUpperCase()), brand, ClothDurability.valueOf(durability.toUpperCase()), buttonNumber, Boolean.parseBoolean(hasCap));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(coat);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Height (per meter)
                                                    11- Pocket Number
                                                    12- Does it have Zipper?
                                                    \t- true, false
                                                    """);
                                            double height = input.nextDouble();
                                            input.nextLine();
                                            int pocketNumber = input.nextInt();
                                            input.nextLine();
                                            String hasZipper = input.nextLine();
                                            Jean jean = new Jean(name, color, quantity, price, currentSeller.getAccountID(), ClothSize.valueOf(size.toUpperCase()), ClothGender.valueOf(clothGender.toUpperCase()), ClothMaterial.valueOf(clothMaterial.toUpperCase()), brand, ClothDurability.valueOf(durability.toUpperCase()), height, pocketNumber, Boolean.parseBoolean(hasZipper));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(jean);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Button Number
                                                    11- Design (for example : dragon on the arms, creepy stuff)
                                                    """);
                                            int buttonNumber = input.nextInt();
                                            input.nextLine();
                                            String design = input.nextLine();
                                            Sweater sweater = new Sweater(name, color, quantity, price, currentSeller.getAccountID(), ClothSize.valueOf(size.toUpperCase()), ClothGender.valueOf(clothGender.toUpperCase()), ClothMaterial.valueOf(clothMaterial.toUpperCase()), brand, ClothDurability.valueOf(durability.toUpperCase()), buttonNumber, design);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(sweater);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 4 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Laptop
                                            2- Smart Phone
                                            3- Smart Watch
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Brand
                                            6- Model
                                            7- OS
                                            8- Screen Size (for example: 10 inches)
                                            9- Battery Capacity
                                            """);
                                    String brand = input.nextLine();
                                    String model = input.nextLine();
                                    String OS = input.nextLine();
                                    String screenSize = input.nextLine();
                                    double batteryCapacity = input.nextDouble();
                                    input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Webcam Model
                                                    11- CPU
                                                    12- GPU
                                                    13- Fan Number
                                                    14- Does it have Keyboard Light?
                                                    \t- true, false
                                                    15- Does it have Finger Print Recognition System?
                                                    \t- true, false
                                                    16- Keyboard Language
                                                    17- Port Number
                                                    """);
                                            String webcamModel = input.nextLine();
                                            String CPU = input.nextLine();
                                            String GPU = input.nextLine();
                                            int fanNumber = input.nextInt();
                                            input.nextLine();
                                            String hasKeyboardLight = input.nextLine();
                                            String hasFingerPrint = input.nextLine();
                                            String keyboardLanguage = input.nextLine();
                                            int portNumber = input.nextInt();
                                            input.nextLine();
                                            Laptop laptop = new Laptop(name, color, quantity, price, currentSeller.getAccountID(), brand, model, OS, screenSize, batteryCapacity, webcamModel, CPU, GPU, fanNumber, Boolean.parseBoolean(hasKeyboardLight), Boolean.parseBoolean(hasFingerPrint), keyboardLanguage, portNumber);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(laptop);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    1- Rear Camera Quality
                                                    2- Selfie Camera Quality
                                                    3- Camera Number
                                                    4- Storage(per Gigabyte)
                                                    5- OS Version (for example : android 11.1)
                                                    6- Display Resolution
                                                    7- Ring Tone
                                                    8- CPU
                                                    """);
                                            int rearCameraQuality = input.nextInt();
                                            input.nextLine();
                                            int selfieCameraQuality = input.nextInt();
                                            input.nextLine();
                                            int cameraNumber = input.nextInt();
                                            input.nextLine();
                                            int storage = input.nextInt();
                                            input.nextLine();
                                            double OSVersion = input.nextDouble();
                                            input.nextLine();
                                            String displayResolution = input.nextLine();
                                            String ringTone = input.nextLine();
                                            String CPU = input.nextLine();
                                            SmartPhone smartPhone = new SmartPhone(name, color, quantity, price, currentSeller.getAccountID(), brand, model, OS, screenSize, batteryCapacity, rearCameraQuality, selfieCameraQuality, cameraNumber, storage, OSVersion, displayResolution, ringTone, CPU);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(smartPhone);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    1- Processor
                                                    2- Does it have Heart Rate Tracker?
                                                    \t- true, false
                                                    3- Does it have Step Tracker?
                                                    \t- true, false
                                                    4- Does it have Caloric Tracker?
                                                    \t- true, false
                                                    """);
                                            String processor = input.nextLine();
                                            String hasHeartRateTracker = input.nextLine();
                                            String hasStepTracker = input.nextLine();
                                            String hasCaloricTracker = input.nextLine();
                                            SmartWatch smartWatch = new SmartWatch(name, color, quantity, price, currentSeller.getAccountID(), brand, model, OS, screenSize, batteryCapacity, processor, Boolean.parseBoolean(hasHeartRateTracker), Boolean.parseBoolean(hasStepTracker), Boolean.parseBoolean(hasCaloricTracker));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(smartWatch);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 5 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Air Conditioner
                                            2- Refrigerator
                                            3- TV
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Does it have Controller?
                                            \t- true, false
                                            6- Height
                                            7- Width
                                            8- Weight
                                            """);
                                    String hasController = input.nextLine();
                                    double height = input.nextDouble();
                                    input.nextLine();
                                    double width = input.nextDouble();
                                    input.nextLine();
                                    double weight = input.nextDouble();
                                    input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    9- Cooling Capacity
                                                    10- Energy Efficiency
                                                    11- Air Filterer
                                                    12- Fan Number
                                                    13- Does it have Remote Control?
                                                    \t- true, false
                                                    14- Does it have Timer?
                                                    \t- true, false
                                                    """);
                                            double coolingCapacity = input.nextDouble();
                                            input.nextLine();
                                            double energyEfficiency = input.nextDouble();
                                            input.nextLine();
                                            String airFilter = input.nextLine();
                                            int fanNumber = input.nextInt();
                                            input.nextLine();
                                            String hasRemoteControl = input.nextLine();
                                            String hasTimer = input.nextLine();
                                            AirConditioner airConditioner = new AirConditioner(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasController), height, width, weight, coolingCapacity, energyEfficiency, airFilter, fanNumber, Boolean.parseBoolean(hasRemoteControl), Boolean.parseBoolean(hasTimer));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(airConditioner);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    9- Floor Number
                                                    10- Does it have Fridge?
                                                    \t- true, false
                                                    11- Refrigerator Type
                                                    \t- SideBySide, FrenchDoor, Compact, Wine
                                                    12- Does it have Digital Controlling System?
                                                    \t- true, false
                                                    """);
                                            int floorNumber = input.nextInt();
                                            input.nextLine();
                                            String hasFridge = input.nextLine();
                                            String Refrigerator_type = input.nextLine();
                                            String hasDigitalController = input.nextLine();
                                            Refrigerator refrigerator = new Refrigerator(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasController), height, width, height, floorNumber, Boolean.parseBoolean(hasFridge), RefrigeratorType.valueOf(Refrigerator_type.toUpperCase()), Boolean.parseBoolean(hasDigitalController));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(refrigerator);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    9- Refresh Rate
                                                    10- Is Mountable on the Wall?
                                                    \t- true, false
                                                    11- Does it have 3D Feature?
                                                    \t- true, false
                                                    12- Does it have Stand?
                                                    \t- true, false
                                                    """);
                                            int refreshRate = input.nextInt();
                                            input.nextLine();
                                            String mountableOnWall = input.nextLine();
                                            String has3D = input.nextLine();
                                            String hasStand = input.nextLine();
                                            TV tv = new TV(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasController), height, width, weight, refreshRate, Boolean.parseBoolean(mountableOnWall), Boolean.parseBoolean(has3D), Boolean.parseBoolean(hasStand));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(tv);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 6 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Balls
                                            2- Gloves
                                            3- Rackets
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Weight
                                            6- Sport Type (for example : American Football, Soccer, Baseball...)
                                            7- Brand
                                            """);
                                    double weight = input.nextDouble();
                                    input.nextLine();
                                    String sportType = input.nextLine();
                                    String brand = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    8- Ball Size
                                                    \t- For kids, TeenAges, Young, Adult
                                                    9-  Ball Material
                                                    \t- Leather, RUBBER, PU, PVC, FOAM
                                                    10- Is Right Hand Oriented?
                                                    \t- true, false
                                                    """);
                                            String ball_size = input.nextLine();
                                            String ball_material = input.nextLine();
                                            String isRightHandOriented = input.nextLine();
                                            Ball ball = new Ball(name, color, quantity, price, currentSeller.getAccountID(), weight, sportType, brand, BallSize.valueOf(ball_size.toUpperCase()), BallMaterial.valueOf(ball_material.toUpperCase()), Boolean.parseBoolean(isRightHandOriented));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(ball);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    8- Gloves Material
                                                    \t- Leather, Neoprene, Mesh, Rubber, Fleece
                                                    9- Gloves Size
                                                    \t- Small, Medium, Large
                                                    10- Gloves Suggested Users
                                                    \t- Mens, Females, Unisex
                                                    11- Gloves Style
                                                    \t- Classic, Fingerless, Mittens, TouchScreen, Fashion
                                                    """);
                                            String gloveMaterial = input.nextLine();
                                            String gloveSize = input.nextLine();
                                            String gloveUser = input.nextLine();
                                            String gloveStyle = input.nextLine();
                                            Gloves glove = new Gloves(name, color, quantity, price, currentSeller.getAccountID(), weight, sportType, brand, GloveMaterial.valueOf(gloveMaterial.toUpperCase()), GloveSize.valueOf(gloveSize.toUpperCase()), GloveUser.valueOf(gloveUser.toUpperCase()), GloveStyle.valueOf(gloveStyle.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(glove);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    8- Length
                                                    9- Width
                                                    10- Durability
                                                    \t- Low, Medium, High
                                                    11- Shape
                                                    """);
                                            double length = input.nextDouble();
                                            input.nextLine();
                                            double width = input.nextDouble();
                                            input.nextLine();
                                            String racketDurability = input.nextLine();
                                            String shape = input.nextLine();
                                            Rackets racket = new Rackets(name, color, quantity, price, currentSeller.getAccountID(), weight, sportType, brand, length, width, RacketDurability.valueOf(racketDurability.toUpperCase()), shape);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(racket);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 7 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Dairy
                                            2- Drink
                                            3- Protein
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Does it have Box?
                                            \t true, false
                                            6- Weight
                                            7- Salt
                                            8- Calories
                                            9- Fat
                                            10- Sugar
                                            11- Country Of Origin
                                            """);
                                    String hasBox = input.nextLine();
                                    double weight = input.nextDouble();
                                    input.nextLine();
                                    double salt = input.nextDouble();
                                    input.nextLine();
                                    double calories = input.nextDouble();
                                    input.nextLine();
                                    double fat = input.nextDouble();
                                    input.nextLine();
                                    double sugar = input.nextDouble();
                                    input.nextLine();
                                    String countryOfOrigin = input.nextLine();
                                    System.out.println("Enter Ingredient items one after the other and enter -1 when you are finished\n");
                                    ArrayList<String> ingredientItems = new ArrayList<>();
                                    while (true) {
                                        String item = input.nextLine();
                                        if (!item.equals("-1")) {
                                            ingredientItems.add(item);
                                        } else {
                                            break;
                                        }
                                    }
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    12- Is Domestic?
                                                    \t- true, false
                                                    13- Dairy Group
                                                    \t- Yogurt, Curd, Cheese, SourCream, Milk, Butter
                                                    """);
                                            String isDomestic = input.nextLine();
                                            String dairyGroup = input.nextLine();
                                            Dairy dairyProduct = new Dairy(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin, Boolean.parseBoolean(isDomestic), DairyGroups.valueOf(dairyGroup.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(dairyProduct);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    12- What does it Taste?(for example : dragon fruit or ...)
                                                    13- Is Soft Drink?
                                                    \t- true, false
                                                    14- Litters
                                                    15- Drink Size
                                                    \t- Individual, Family
                                                    """);
                                            String taste = input.nextLine();
                                            String isSoftDrink = input.nextLine();
                                            double litters = input.nextDouble();
                                            input.nextLine();
                                            String size = input.nextLine();
                                            Drinks drink = new Drinks(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin, taste, Boolean.parseBoolean(isSoftDrink), litters, DrinkSize.valueOf(size.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(drink);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    12- Brand
                                                    13- Protein Amount
                                                    14- Product Type
                                                    \t- Bologna, Sausage, ChickenMeat, Fish, Shrimp, BirdEggs
                                                    """);
                                            String brand = input.nextLine();
                                            double proteinAmount = input.nextDouble();
                                            input.nextLine();
                                            String productType = input.nextLine();
                                            Proteins protein = new Proteins(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), weight, salt, calories, fat, sugar, ingredientItems, countryOfOrigin, brand, proteinAmount, ProteinProductType.valueOf(productType.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(protein);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 8 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Drill
                                            2- Soldering System
                                            3- Spanner
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Weight
                                            6- Does it have Box?
                                            \t- true, false
                                            7- Is Silent?
                                            \t- true, false
                                            8- Is Chargeable?
                                            \t- true, false
                                            9- Brand
                                            """);
                                    double weight = input.nextDouble();
                                    input.nextLine();
                                    String hasBox = input.nextLine();
                                    String isSilent = input.nextLine();
                                    String isChargeable = input.nextLine();
                                    String brand = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Voltage
                                                    11- Power Source
                                                    \t- AC, DC
                                                    12- Minimum Spin Speed
                                                    13- Maximum Spin Speed
                                                    """);
                                            int voltage = input.nextInt();
                                            input.nextLine();
                                            String powerSource = input.nextLine();
                                            int minSpinSpeed = input.nextInt();
                                            input.nextLine();
                                            int maxSpinSpeed = input.nextInt();
                                            input.nextLine();
                                            Drill drill = new Drill(name, color, quantity, price, currentSeller.getAccountID(), weight, Boolean.parseBoolean(hasBox), Boolean.parseBoolean(isSilent), Boolean.parseBoolean(isChargeable), brand, voltage, PowerSource.valueOf(powerSource.toUpperCase()), minSpinSpeed, maxSpinSpeed);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(drill);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Voltage
                                                    11- Power Source
                                                    \t- AC, DC
                                                    12- Usage Level
                                                    \t- Beginner, Intermediate, Professional
                                                    """);
                                            int voltage = input.nextInt();
                                            input.nextLine();
                                            String powerSource = input.nextLine();
                                            String usageLevel = input.nextLine();
                                            SolderingSystem solderingSystem = new SolderingSystem(name, color, quantity, price, currentSeller.getAccountID(), weight, Boolean.parseBoolean(hasBox), Boolean.parseBoolean(isSilent), Boolean.parseBoolean(isChargeable), brand, voltage, PowerSource.valueOf(powerSource.toUpperCase()), UsageLevel.valueOf(usageLevel.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(solderingSystem);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    10- Size (Thickness in millimeter, don't enter floats)
                                                    11- Style (for example : Combination open end / 12 point / 15° / Offset ring end)
                                                    12- Material
                                                    \t- Steel, Aluminium, Titanium, Plastic, Composite
                                                    """);
                                            int size = input.nextInt();
                                            input.nextLine();
                                            String style = input.nextLine();
                                            String material = input.nextLine();
                                            Spanner spanner = new Spanner(name, color, quantity, price, currentSeller.getAccountID(), weight, Boolean.parseBoolean(hasBox), Boolean.parseBoolean(isSilent), Boolean.parseBoolean(isChargeable), brand, size, style, SpannerMaterial.valueOf(material.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(spanner);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 9 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Board Game
                                            2- Card Game
                                            3- Puzzle
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Does it have Box?
                                            \t- true, false
                                            6- Difficulty Level
                                            \t- Easy, Normal, Hard
                                            7- Is Multiplayer?
                                            \t true, false
                                            """);
                                    String hasBox = input.nextLine();
                                    String difficultyLevel = input.nextLine();
                                    String isMultiplayer = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    8- Size (for example : 2 by 2)
                                                    9- Player Number
                                                    10- Time To Finish(per minute)
                                                    """);
                                            String size = input.nextLine();
                                            int playerNumber = input.nextInt();
                                            input.nextLine();
                                            int timeToFinish = input.nextInt();
                                            input.nextLine();
                                            BoardGames boardGame = new BoardGames(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), DifficultyLevel.valueOf(difficultyLevel.toUpperCase()), Boolean.parseBoolean(isMultiplayer), size, playerNumber, timeToFinish);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(boardGame);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    8- Card Number
                                                    9- Player Number
                                                    10- Gang Number
                                                    """);
                                            int cardNumber = input.nextInt();
                                            input.nextLine();
                                            int playerNumber = input.nextInt();
                                            input.nextLine();
                                            int gangNumber = input.nextInt();
                                            input.nextLine();
                                            CardGames cardGame = new CardGames(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), DifficultyLevel.valueOf(difficultyLevel.toUpperCase()), Boolean.parseBoolean(isMultiplayer), cardNumber, playerNumber, gangNumber);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(cardGame);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    1- partNumber
                                                    2- finalPicture
                                                    """);
                                            int partNumber = input.nextInt();
                                            input.nextLine();
                                            String finalPicture = input.nextLine();
                                            Puzzles puzzle = new Puzzles(name, color, quantity, price, currentSeller.getAccountID(), Boolean.parseBoolean(hasBox), DifficultyLevel.valueOf(difficultyLevel.toUpperCase()), Boolean.parseBoolean(isMultiplayer.toUpperCase()), partNumber, finalPicture);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(puzzle);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                case 10 -> {
                                    System.out.println("""
                                            Select one subcategory from below:
                                            1- Car
                                            2- Motorcycle
                                            3- Truck
                                            """);
                                    optionMenu = input.nextInt();
                                    input.nextLine();

                                    System.out.println("""
                                            Enter :
                                            5- Weight
                                            6- Horse Power
                                            7- Engine Model
                                            8- Wheel Number
                                            9- Is Automatic?
                                            \t- true, false
                                            10- Maximum Speed
                                            11- Brand
                                            12- Model
                                            """);
                                    double weight = input.nextDouble();
                                    input.nextLine();
                                    int horsePower = input.nextInt();
                                    input.nextLine();
                                    String engineModel = input.nextLine();
                                    int wheelNumber = input.nextInt();
                                    input.nextLine();
                                    String isAutomatic = input.nextLine();
                                    int maxSpeed = input.nextInt();
                                    input.nextLine();
                                    String brand = input.nextLine();
                                    String model = input.nextLine();
                                    switch (optionMenu) {
                                        case 1 -> {
                                            System.out.println("""
                                                    Enter :
                                                    13- Is Right Steering?
                                                    \t true, false
                                                    14- Speaker Model
                                                    15- Seat Number
                                                    """);
                                            String isRightSteering = input.nextLine();
                                            String speakerModel = input.nextLine();
                                            int seatNumber = input.nextInt();
                                            input.nextLine();
                                            Car car = new Car(name, color, quantity, price, currentSeller.getAccountID(), weight, horsePower, engineModel, wheelNumber, Boolean.parseBoolean(isAutomatic.toUpperCase()), maxSpeed, brand, model, Boolean.parseBoolean(isRightSteering), speakerModel, seatNumber);       //Will be added to products table in the database automatically
                                            shop.addProductToShop(car);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 2 -> {
                                            System.out.println("""
                                                    Enter :
                                                    13- Seat Number
                                                    14- Does it have Wing Mirror ?
                                                    \t- true, false
                                                    15- Noise Level
                                                    \t- Low, Normal, High
                                                    """);
                                            int seatNumber = input.nextInt();
                                            input.nextLine();
                                            String hasWingMirror = input.nextLine();
                                            String noiseLevel = input.nextLine();
                                            Motorcycle motorcycle = new Motorcycle(name, color, quantity, price, currentSeller.getAccountID(), weight, horsePower, engineModel, wheelNumber, Boolean.parseBoolean(isAutomatic), maxSpeed, brand, model, seatNumber, Boolean.parseBoolean(hasWingMirror), NoiseLevel.valueOf(noiseLevel.toUpperCase()));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(motorcycle);       //Will be added to seller available products column in the database automatically
                                        }
                                        case 3 -> {
                                            System.out.println("""
                                                    Enter :
                                                    13- Type of the Truck
                                                    \t- Pickup, Dump, SemiTrailer, Tanker, Box, Fire
                                                    14- Does it have bed?
                                                    \t- true, false
                                                    """);
                                            String truckType = input.nextLine();
                                            String hasBed = input.nextLine();
                                            Truck truck = new Truck(name, color, quantity, price, currentSeller.getAccountID(), weight, horsePower, engineModel, wheelNumber, Boolean.parseBoolean(isAutomatic), maxSpeed, brand, model, TruckType.valueOf(truckType.toUpperCase()), Boolean.parseBoolean(hasBed));       //Will be added to products table in the database automatically
                                            shop.addProductToShop(truck);       //Will be added to seller available products column in the database automatically
                                        }
                                    }
                                }
                                default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                            }
                        }
                        case 2 -> {
                            System.out.println("Enter : - Product ID\n");
                            String productID = input.nextLine();
                            shop.removeProduct(UUID.fromString(productID));
                        }
                        case 3 -> ((Seller) shop.getCurrentAccount()).viewAvailableProducts();
                        default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                    }
                }
                case 2 -> {
                    //View Wallet
                    ((Seller) shop.getCurrentAccount()).viewWallet();
                }
                case 3 -> {
                    //Logout
                    shop.logOut();
                    runMenu(shop);
                }
                case 4 -> {
                    //Back to Main Menu
                    runMenu(shop);
                }
                default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
            }
            sellerPage(shop);
        }
    }

    public static void adminPage(Shop shop) {
        System.out.println("------------------------------ " + shop.getCurrentAccount().getUsername() + " ------------------------------");
        System.out.println("""
                1- Wallets Management
                \t- Show all wallet requests
                \t- Show all confirmed wallet requests
                \t- Show all unconfirmed wallet requests
                \t- Confirm a wallet request by ID
                                
                2- Checkouts & Orders Management
                \t- Show all orders
                \t- Show all confirmed orders
                \t- Show all unconfirmed orders
                \t- Confirm an order by ID
                                
                3- Admins Management
                \t- Add an admin
                                
                4- Users Management
                \t- Show all user wallet requests by userID
                \t- Show user's confirmed wallet requests by userID
                \t- Show user's unconfirmed wallet requests by userID
                \t- Show user's all orders by userID
                \t- Show user's confirmed orders by userID
                \t- Show user's unconfirmed orders by userID
                \t- Watch all profile screens
                \t- Watch an specific profile screen by userID
                                
                5- Sellers Management
                \t- Show all unauthorized sellers
                \t- Authorize a seller by sellerID
                
                6- Logout
                                
                7- Back to main menu
                """);
        int optionMenu = input.nextInt();
        input.nextLine();
        switch (optionMenu) {
            case 1 -> {
                System.out.println("""
                        1- Show all wallet requests
                        2- Show all confirmed wallet requests
                        3- Show all unconfirmed wallet requests
                        4- Confirm a wallet request by ID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> shop.showAllWalletRequests();
                    case 2 -> shop.showAllConfirmedWalletRequests();
                    case 3 -> shop.showAllUnconfirmedWalletRequests();
                    case 4 -> {
                        System.out.println("Enter : - Wallet ID\n");
                        String productID = input.nextLine();
                        shop.walletConfirm(UUID.fromString(productID));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 2 -> {
                System.out.println("""
                        1- Show all orders
                        2- Show all confirmed orders
                        3- Show all unconfirmed orders
                        4- Confirm an order by ID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> shop.showAllOrders();
                    case 2 -> shop.showAllConfirmedOrders();
                    case 3 -> shop.showAllUnconfirmedOrders();
                    case 4 -> {
                        System.out.println("Enter : - Order ID\n");
                        String productID = input.nextLine();
                        shop.orderConfirm(UUID.fromString(productID));      //All the associated updates will be automatically get done in the database
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 3 -> {
                System.out.println("Enter : 1- Username, 2- Password, 3- Email Address");
                String username = input.nextLine();
                String password = input.nextLine();
                String email = input.nextLine();
                shop.adminSignUp(username, password, email);        //Will be automatically added to the Admins table in the database
            }
            case 4 -> {
                System.out.println("""
                        1- Show all user wallet requests by userID
                        2- Show user's confirmed wallet requests by userID
                        3- Show user's unconfirmed wallet requests by userID
                        4- Show user's all orders by userID
                        5- Show user's confirmed orders by userID
                        6- Show user's unconfirmed orders by userID
                        7- Watch all profile screens
                        8- Watch an specific profile screen by userID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showAllUserWalletRequests(UUID.fromString(productID));
                    }
                    case 2 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showUserConfirmedWalletRequests(UUID.fromString(productID));
                    }
                    case 3 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showUserUnconfirmedWalletRequests(UUID.fromString(productID));
                    }
                    case 4 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showUserAllOrders(UUID.fromString(productID));
                    }
                    case 5 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showUserConfirmedOrders(UUID.fromString(productID));
                    }
                    case 6 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.showUserUnconfirmedOrders(UUID.fromString(productID));
                    }
                    case 7 -> shop.userProfileScreens();
                    case 8 -> {
                        System.out.println("Enter : - User ID");
                        String productID = input.nextLine();
                        shop.userProfileScreen(UUID.fromString(productID));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 5 -> {
                System.out.println("""
                        1- Show all unauthorized sellers
                        2- Authorize a seller by sellerID
                        """);
                optionMenu = input.nextInt();
                input.nextLine();
                switch (optionMenu) {
                    case 1 -> shop.showUnauthorizedSellers();
                    case 2 -> {
                        System.out.println("Enter : - Seller ID");
                        String sellerID = input.nextLine();
                        shop.sellerAuthorization(UUID.fromString(sellerID));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
                }
            }
            case 6 -> {
                //Logout
                shop.logOut();
                runMenu(shop);
            }
            case 7 -> runMenu(shop);
            default -> throw new IllegalStateException("Unexpected value: " + optionMenu);
        }
        adminPage(shop);
    }
}