package Main.Entities;

import Main.Util.BlockchainService;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * A donation made by a user to the public
 */
public class Donation {

    private UUID donationID;
    private String donorName;
    private int amount;
    private LocalDateTime donationDate;
    private boolean anonymous;
    private String charity;

    public Donation(int amount, String name, String charity) {
        donationID = BlockchainService.createTransaction();
        this.amount = amount;
        this.anonymous = (name == null || name.equals("anonymous"));
        this.donorName = this.anonymous ? "Anonymous" : name;
        this.donationDate = LocalDateTime.now();
        this.charity = charity;
        // Implement this donationDate = Date.;
    }

    public UUID getDonationID() {
        return donationID;
    }

    public void setDonationID(UUID donationID) {
        this.donationID = donationID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDateTime donationDate) {
        this.donationDate = donationDate;
    }

    /**
     * @return Returns donation info
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(donationID.toString());
        str.append(" : ");
        if (anonymous) {
            str.append("anonymous");
        } else {
            str.append(this.donorName);
        }
        str.append(" : ");
        str.append("Thank you for donating $");
        str.append(this.amount);
        return str.toString();

    }


    public String getCharity() {
        return charity;
    }

    public void setCharity(String charity) {
        this.charity = charity;
    }

    public boolean upload2DB() {
        return SqlDriver.insertDonation(this.getDonationID(), this.getDonorName(), this.amount, this.getDonationDate().toString(), this.charity);
    }
}
