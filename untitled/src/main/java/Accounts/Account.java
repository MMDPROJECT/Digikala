package Accounts;

import java.util.UUID;

public abstract class Account {
    private final UUID accountID;

    //Constructor

    public Account() {
        this.accountID = UUID.randomUUID();
    }

    public Account(UUID accountID) {
        this.accountID = accountID;
    }

    //Getters and Setters

    public UUID getAccountID() {
        return accountID;
    }

    //Override

    @Override
    public String toString() {
        return "Account{" +
                "Account ID=" + accountID +
                '}';
    }

    //Abstract methods

    public abstract boolean accountLogin(String username, String password);

    public abstract boolean doesAccountExist(String username);

    public abstract String getUsername();
}
