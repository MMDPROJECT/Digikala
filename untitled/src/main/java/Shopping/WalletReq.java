package Shopping;

import Accounts.User;

import java.util.UUID;

public class WalletReq {
    private final double value;
    private final User user;
    private final UUID id;
    private boolean isConfirmed;

    //Constructor

    public WalletReq(double value, User user) {
        this.value = value;
        this.isConfirmed = false;
        this.user = user;
        this.id = UUID.randomUUID();
    }


    //Getters and Setters


    public double getValue() {
        return value;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public User getUser() {
        return user;
    }

    public UUID getId() {
        return id;
    }

    //Override

    @Override
    public String toString() {
        return "WalletReq{" +
                "value=" + value +
                ", isConfirmed=" + isConfirmed +
                ", user=" + user.getUsername() +
                ", Wallet ID=" + id +
                '}';
    }

    //Wallet - Related Methods

    public void walletConfirm() {
        this.isConfirmed = true;
        this.user.addWallet(this.value);
    }
}
