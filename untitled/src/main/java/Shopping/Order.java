package Shopping;

import Accounts.User;
import Categories.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Order extends ShoppingCart {
    private final LocalDate date;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    private final User buyer;
    private final UUID id;
    private boolean isConfirmed;

    //Constructor

    public Order(String name, ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, double totalPrice, User buyer) {
        super(name, products, itemNumber, totalPrice);
        this.buyer = buyer;
        this.id = UUID.randomUUID();
        this.date = LocalDate.from(LocalDateTime.now());
        this.isConfirmed = false;
    }


    //Getters and Setters


    public LocalDate getDate() {
        return date;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public User getBuyer() {
        return buyer;
    }


    public UUID getId() {
        return id;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    //Override

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", buyer=" + buyer.getUsername() +
                ", Order ID=" + id +
                ", isConfirmed=" + isConfirmed +
                "} " + super.toString();
    }

    //Order - Related Methods

    public void updateStocks() {
        for (Product product : getProducts()) {
            product.decreaseProduct(getItemNumber().get(product.getId()));
        }
        System.out.println("Stocks has been successfully updated!\n");
    }

    public void updateUserPurchasedProducts() {
        for (Product product : getProducts()) {
            if (!buyer.isProductPurchased(product.getId())) {
                buyer.addPurchasedProduct(product);
            }
        }
        System.out.println("User purchased product has been successfully updated!\n");
    }

    public double calcBuyerPayOff() {
        double total = 0;
        for (Product product : this.getProducts()) {
            total += product.getPrice() * getItemNumber().get(product.getId());
        }
        return total;
    }

    public double calcShopCut() {
        double cut = 0;
        for (Product product : this.getProducts()) {
            cut += 0.1 * (product.getPrice() * this.getItemNumber().get(product.getId()));
        }
        return cut;
    }


    public void orderConfirm() {
        this.isConfirmed = true;
    }
}
