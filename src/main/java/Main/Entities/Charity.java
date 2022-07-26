package Main.Entities;

import java.util.List;

/**
 * Charity object
 */
public class Charity {
    private String name;
    private String user;


    private String password;
    private Integer walletBalance;

    public Charity(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getWalletBalance() {
        return walletBalance;
    }

    public List<CharityPayment> getPayments() {
        return SqlDriver.selectCharitySpending(name);
    }


}
