package Shopping;

import Accounts.Seller;
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
    private final Seller seller;
    private final UUID id;
    private boolean isConfirmed;

    //Constructor

    public Order(ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, double totalPrice, User buyer, Seller seller) {
        super(products, itemNumber, totalPrice);
        this.buyer = buyer;
        this.seller = seller;
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

    public Seller getSeller() {
        return seller;
    }

    public UUID getId() {
        return id;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }
    //Override

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", dateTimeFormatter=" + dateTimeFormatter +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", id=" + id +
                ", isConfirmed=" + isConfirmed +
                "} " + super.toString();
    }

    //Order - Related Methods

    public void updateStocks() {
        for (Product product : getProducts()) {
            product.decreaseProduct(getItemNumber().get(product.getId()));
        }
        System.out.println("Stock has been successfully updated!\n");
    }

    public void orderConfirm() {
        this.isConfirmed = true;
    }
}
