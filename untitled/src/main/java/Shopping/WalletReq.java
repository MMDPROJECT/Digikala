package Shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static Connection.Connect.connect;

public class WalletReq {
    private final double value;
    private final UUID userID;
    private final UUID walletID;
    private boolean isConfirmed;

    //Constructor

    public WalletReq(double value, UUID userID) {
        this.value = value;
        this.isConfirmed = false;
        this.userID = userID;
        this.walletID = UUID.randomUUID();
        insert();
    }

    public WalletReq(double value, UUID userID, UUID walletID, boolean isConfirmed) {
        this.value = value;
        this.userID = userID;
        this.walletID = walletID;
        this.isConfirmed = isConfirmed;
    }

    //Getters and Setters

    public void insert() {
        String sql = "INSERT INTO WalletRequest(value, userID, WalletRequestID, isConfirmed) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, value);
            pstmt.setString(2, userID.toString());
            pstmt.setString(3, walletID.toString());
            pstmt.setString(4, Boolean.toString(isConfirmed));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double getValue() {
        return value;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public UUID getUser() {
        return userID;
    }

    public UUID getWalletID() {
        return walletID;
    }

    public UUID getUserID() {
        return userID;
    }

    //Override

    public void setConfirmed() {
        this.isConfirmed = true;
    }

    //Wallet - Related Methods

    @Override
    public String toString() {
        return "WalletReq{" +
                "value=" + value +
                ", userID=" + userID +
                ", walletID=" + walletID +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
