package Shopping;

import Categories.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ShoppingCart {
    private final ArrayList<Product> products;
    private final HashMap<UUID, Integer> itemNumber;  //A hashmap to store amount of each product that we have in the cart
    private final UUID id;
    private final String name;
    private double totalPrice;

    //Constructor

    public ShoppingCart(String name, ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, double totalPrice) {
        this.name = name;
        this.products = products;
        this.itemNumber = itemNumber;
        this.totalPrice = totalPrice;
        this.id = UUID.randomUUID();
    }

    public ShoppingCart(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        this.itemNumber = new HashMap<>();
        this.totalPrice = 0;
        this.id = UUID.randomUUID();
    }
    //Getters and Setters

    public ArrayList<Product> getProducts() {
        return products;
    }

    public HashMap<UUID, Integer> getItemNumber() {
        return itemNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //Cart - Related Methods

    public boolean doesProductExist(Product product) {
        return products.contains(product);
    }

    public boolean doesProductExist(UUID id) {
        return itemNumber.containsKey(id);
    }

    public Product getProduct(UUID id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product, int amount) {
        if (product.getQuantity() >= amount) {
            products.add(product);
            itemNumber.put(product.getId(), amount);
            System.out.println("Product has been successfully added to the cart!\n");
        } else {
            System.out.println("Remaining stock is not enough\n");
        }
        totalPrice = updateTotalPrice();
    }

    public void increaseAmount(UUID id, int amount) {
        if (getProduct(id).getQuantity() >= amount + itemNumber.get(id)) {
            itemNumber.replace(id, itemNumber.get(id) + amount);
            System.out.println("Cart has been successfully updated!\n");
        } else {
            System.out.println("Remaining stock is not enough\n");
        }
        totalPrice = updateTotalPrice();
    }

    public void decreaseAmount(UUID id, int amount) {
        if (itemNumber.get(id) >= amount) {
            getProduct(id).increaseProduct(amount);
            itemNumber.replace(id, itemNumber.get(id) - amount);
            System.out.println("Cat has been successfully updated!\n");
        } else {
            System.out.println("Something went wrong! Please try again\n");
        }
        totalPrice = updateTotalPrice();
    }

    public void removeProduct(UUID id) {
        products.remove(getProduct(id));
        itemNumber.remove(id);
        System.out.println("Product has been successfully removed!\n");
        totalPrice = updateTotalPrice();
    }

    public void viewCart() {
        if (products.size() == 0) {
            System.out.println("Cart is empty!\n");
        } else {
            for (Product product : products) {
                System.out.println(product + "Amount has been added to cart=" + itemNumber.get(product.getId()));
            }
            System.out.println("Total Price=" + totalPrice + "\n");
        }
    }

    public double updateTotalPrice() {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * itemNumber.get(product.getId());
        }
        return totalPrice;
    }

    //Override

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", products=" + products +
                ", itemNumber=" + itemNumber +
                ", id=" + id +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
