package Accounts;

import java.util.UUID;

public abstract class Account {
    private UUID id;

    //Constructor

    public Account() {
        this.id = UUID.randomUUID();
    }

    //Getters and Setters

    public UUID getId() {
        return id;
    }

    //Override

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                '}';
    }
}
