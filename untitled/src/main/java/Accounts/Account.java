package Accounts;

import java.util.UUID;

public abstract class Account {
    private final UUID id;

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
                "Account ID=" + id +
                '}';
    }

    //Abstract methods

    public abstract boolean accountLogin(String username, String password);

    public abstract boolean doesAccountExist(String username);

    public abstract String getUsername();
}
