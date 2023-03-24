package Accounts;

import Categories.Product;

import java.util.HashMap;
import java.util.UUID;

public class Seller {
    private String companyName;
    private String password;
    private UUID id;
    private HashMap<UUID, Product> availableProducts;
    private double wallet;

    //Constructor

    public Seller(String companyName, String password, double wallet) {
        this.companyName = companyName;
        this.password = password;
        this.wallet = wallet;
        this.id = UUID.randomUUID();
        this.availableProducts = new HashMap<>();
        this.wallet = 0;
    }

    //Getters and Setters

    public String getCompanyName() {
        return companyName;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public HashMap<UUID, Product> getAvailableProducts() {
        return availableProducts;
    }

    public double getWallet() {
        return wallet;
    }

    //Override

    @Override
    public String toString() {
        return "Seller{" +
                "companyName='" + companyName + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", availableProducts=" + availableProducts +
                ", wallet=" + wallet +
                '}';
    }

    //Seller - Related Methods

    public boolean doesProductExist(Product product){
        for (UUID id : availableProducts.keySet()){
            if (availableProducts.get(id).equals(product)){
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product){
        availableProducts.put(product.getId(), product);
    }

    public void removeProduct(Product product){
        availableProducts.remove(product.getId());
    }

    public void removeProduct(UUID id){
        availableProducts.remove(id);
    }

    public void viewAvailableProducts(){
        if (availableProducts.size() == 0){
            System.out.println("No product has been added yet!\n");
        }
        else {
            for (UUID id : availableProducts.keySet()){
                System.out.println(availableProducts.get(id));
            }
        }
    }
}
