package Shopping;

import Categories.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static Connection.Connect.connect;

public class Order extends ShoppingCart {
    private final LocalDate date;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    private final UUID buyerID;
    private final UUID orderID;
    private boolean isConfirmed;

    //Constructor

    public Order(String name, ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, double totalPrice, UUID buyerID) {
        super(name, products, itemNumber, totalPrice);
        this.buyerID = buyerID;
        this.orderID = UUID.randomUUID();
        this.date = LocalDate.from(LocalDateTime.now());
        this.isConfirmed = false;
        insert();
    }

    public Order(ArrayList<Product> products, HashMap<UUID, Integer> itemNumber, UUID cartID, UUID userID, String name, double totalPrice, boolean hasCheckout, LocalDate date, UUID buyerID, UUID orderID, boolean isConfirmed) {
        super(products, itemNumber, cartID, userID, name, totalPrice, hasCheckout);
        this.date = date;
        this.buyerID = buyerID;
        this.orderID = orderID;
        this.isConfirmed = isConfirmed;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO Orders(date, dateTimeFormatter, buyerID, orderID, isConfirmed, itemNumber, totalPrice) VALUES(?,?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date.toString());
            pstmt.setString(2, dateTimeFormatter.toString());
            pstmt.setString(3, buyerID.toString());
            pstmt.setString(4, orderID.toString());
            pstmt.setString(5, Boolean.toString(isConfirmed));
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(getItemNumber());
            pstmt.setString(6, json);
            pstmt.setDouble(7, getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public UUID getBuyer() {
        return buyerID;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    //Override

    public UUID getBuyerID() {
        return buyerID;
    }

    //Order - Related Methods

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", dateTimeFormatter=" + dateTimeFormatter +
                ", buyerID=" + buyerID +
                ", orderID=" + orderID +
                ", isConfirmed=" + isConfirmed +
                "} " + super.toString();
    }

    //Order - Related methods

    public void updateStocks() {
        for (Product product : getProducts()) {
            product.decreaseProduct(getItemNumber().get(product.getProductID()));
        }
    }

    public double calcBuyerPayOff() {
        double total = 0;
        for (Product product : this.getProducts()) {
            total += product.getPrice() * getItemNumber().get(product.getProductID());
        }
        return total;
    }

    public double calcShopCut() {
        double cut = 0;
        for (Product product : this.getProducts()) {
            cut += 0.1 * (product.getPrice() * this.getItemNumber().get(product.getProductID()));
        }
        return cut;
    }

    public void orderConfirm() {
        this.isConfirmed = true;
        updateOrderInDatabase();
    }

    //Database - Related methods

    public void updateOrderInDatabase() {
        String sql = "UPDATE Orders SET isConfirmed = ?, itemNumber = ?, totalPrice = ? WHERE orderID = ?";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ObjectMapper objectMapper = new ObjectMapper();
            String itemNumberJson = objectMapper.writeValueAsString(getItemNumber());
            stmt.setString(1, Boolean.toString(true));
            stmt.setString(2, itemNumberJson);
            stmt.setDouble(3, getTotalPrice());
            stmt.setString(4, orderID.toString());
            stmt.executeUpdate();
            System.out.println("Order has been successfully updated in Database!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
