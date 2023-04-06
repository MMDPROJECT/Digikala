package Shopping;

import Categories.Product;
import Shop.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static Connection.Connect.connect;

public class ShoppingCart {
    private final String name;
    private final UUID cartID;
    private final UUID userID;
    private final ArrayList<Product> products;
    private final HashMap<UUID, Integer> itemNumber;  //A hashmap to store amount of each product that we have in the cart
    private double totalPrice;
    private boolean hasCheckout;

    //Constructor

    public ShoppingCart(String name, ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, double totalPrice) {
        this.name = name;
        this.products = products;
        this.itemNumber = itemNumber;
        this.totalPrice = totalPrice;
        this.cartID = UUID.randomUUID();
        this.userID = UUID.randomUUID();
        this.hasCheckout = false;
    }

    public ShoppingCart(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        this.itemNumber = new HashMap<>();
        this.totalPrice = 0;
        this.cartID = UUID.randomUUID();
        this.userID = UUID.randomUUID();
        insert();   //Add to database
    }

    public ShoppingCart(ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, UUID cartID, UUID userID, String name, double totalPrice, boolean hasCheckout) {
        this.products = products;
        this.itemNumber = itemNumber;
        this.cartID = cartID;
        this.userID = userID;
        this.name = name;
        this.totalPrice = totalPrice;
        this.hasCheckout = hasCheckout;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Carts(name, cartID, userID, totalPrice, hasCheckout, itemNumber) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, cartID.toString());
            pstmt.setString(3, userID.toString());
            pstmt.setDouble(4, totalPrice);
            pstmt.setString(5, Boolean.toString(hasCheckout));
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonItemNumber = objectMapper.writeValueAsString(itemNumber);
                pstmt.setString(6, jsonItemNumber);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public HashMap<UUID, Integer> getItemNumber() {
        return this.itemNumber;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public UUID getOrderID() {
        return this.cartID;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasCheckout() {
        return this.hasCheckout;
    }

    public UUID getCartID() {
        return cartID;
    }

    public UUID getUserID() {
        return userID;
    }

    //Cart - Related Methods

    public double updateTotalPrice() {
        int totalPrice = 0;
        for (Product product : this.products) {
            totalPrice += product.getPrice() * this.itemNumber.get(product.getProductID());
            updateCartInDatabase();
        }
        return totalPrice;
    }

    public void addProductToCart(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            this.products.add(product);
            this.itemNumber.put(product.getProductID(), quantity);
            this.totalPrice = updateTotalPrice();
            updateCartInDatabase();
            System.out.println("Product has been successfully added to the cart!\n");
        } else {
            System.out.println("Remaining stock is not enough\n");
        }
    }

    public void increaseQuantityInCart(UUID id, int amount) {
        if (getProduct(id).getQuantity() >= amount + this.itemNumber.get(id)) {
            this.itemNumber.replace(id, this.itemNumber.get(id) + amount);
            this.totalPrice = updateTotalPrice();
            updateCartInDatabase();
            System.out.println("Cart has been successfully updated!\n");
        } else {
            System.out.println("Remaining stock is not enough\n");
        }
    }

    public void decreaseQuantityInCart(UUID id, int amount) {
        if (this.itemNumber.get(id) >= amount) {
            getProduct(id).increaseProduct(amount);
            this.itemNumber.replace(id, this.itemNumber.get(id) - amount);
            this.totalPrice = updateTotalPrice();
            updateCartInDatabase();
            System.out.println("Cart has been successfully updated!\n");
        } else {
            System.out.println("Something went wrong! Please try again\n");
        }
    }

    public void removeProduct(UUID id) {
        if (doesProductExist(id)) {
            this.products.remove(getProduct(id));
            this.itemNumber.remove(id);
            System.out.println("Product has been successfully removed!\n");
            this.totalPrice = updateTotalPrice();
            updateCartInDatabase();
        } else {
            System.out.println("Product has not been found!\n");
        }
    }

    public void checkoutCart() {
        this.hasCheckout = true;
        updateCartInDatabase();
    }

    //Existence - Related methods

    public boolean doesProductExist(UUID id) {
        return this.itemNumber.containsKey(id);
    }

    public Product getProduct(UUID id) {
        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void viewCart() {
        if (products.size() == 0) {
            System.out.println("Cart is empty!\n");
        } else {
            for (Product product : products) {
                System.out.println(product + "Amount has been added to cart=" + itemNumber.get(product.getProductID()));
            }
            System.out.println("Total Price=" + totalPrice + "\n");
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", cartID=" + cartID +
                ", userID=" + userID +
                ", products=" + products +
                ", itemNumber=" + itemNumber +
                ", totalPrice=" + totalPrice +
                ", hasCheckout=" + hasCheckout +
                '}';
    }

    //Database - Related methods

    public void updateCartInDatabase() {
        String sql = "UPDATE Carts SET totalPrice = ?, hasCheckout = ?, itemNumber = ? WHERE cartID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            try {
                stmt.setDouble(1, totalPrice);
                stmt.setString(2, Boolean.toString(hasCheckout));
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(itemNumber);
                stmt.setString(3, json);
                stmt.setString(4, cartID.toString());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate();
            System.out.println("Cart has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadShoppingCartsFromDatabase(Shop shop) {
        String sql = "SELECT * FROM Carts";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                String name = rs.getString("name");
                UUID cartID = UUID.fromString(rs.getString("cartID"));
                UUID userID = UUID.fromString(rs.getString("userID"));
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<UUID, Integer> itemNumber = new HashMap<>();
                try {
                    itemNumber = objectMapper.readValue(rs.getString("itemNumber"), HashMap.class) ; //A hashmap to store amount of each product that we have in the cart
                } catch (JsonProcessingException jsonProcessingException){
                    jsonProcessingException.getMessage();
                }
                ArrayList<Product> products = new ArrayList<>();
                for (UUID productID : itemNumber.keySet()){
                    Product product = shop.getProduct(productID);
                    products.add(product);
                }
                double totalPrice = rs.getDouble("totalPrice");
                boolean hasCheckout = Boolean.parseBoolean(rs.getString("hasCheckout"));
                ShoppingCart newShoppingCart = new ShoppingCart(products, itemNumber, cartID, userID, name, totalPrice, hasCheckout);
                shop.addCart(newShoppingCart);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
