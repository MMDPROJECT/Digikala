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
    private LocalDate date;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    private User buyer;
    private Seller seller;
    private UUID id;
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

    public void orderConfirm(){
        if (this.isConfirmed){
            System.out.println("This order has been confirmed!\n");
        }
        else {
            this.isConfirmed = true;
            System.out.println("Order has been successfully confirmed!\n");
        }
    }
}