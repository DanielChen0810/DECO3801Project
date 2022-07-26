package Main.Entities;

/**
 * Represents a payment uploaded by a charity
 */
public class CharityPayment {
    private String id;
    private String charity;
    private String amount;
    private String description;

    public CharityPayment(String id, String charity, String amount, String description) {
        this.id = id;
        this.charity = charity;
        this.amount = amount;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharity() {
        return charity;
    }

    public void setCharity(String charity) {
        this.charity = charity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
