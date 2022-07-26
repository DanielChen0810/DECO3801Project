package Main.Managers;

import Main.Entities.Donation;
import Main.Entities.SqlDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Singleton donation object manager. Should be instantiated using Spring Beans
 */
@Component
public class DonationManager {
    private HashMap<UUID, Donation> donations;

    public DonationManager() {
        System.out.println("Creating new charity manager");
        donations = new HashMap<>();
        for (Donation donation :
                SqlDriver.selectAllDonation()) {
            donations.put(donation.getDonationID(), donation);
        }
    }

    public HashMap<UUID, Donation> getDonations() {
        return donations;
    }

    public void addDonation(Donation donation) {
        this.donations.put(donation.getDonationID(), donation);
    }

    public Donation getDonation(UUID id) {
        return donations.get(id);
    }

    public List<Donation> getCharityDonations(String charity) {
        List<Donation> charityDonations = new ArrayList<>();
        for (Donation donation : donations.values()) {
            if (donation.getCharity().equals(charity)) {
                charityDonations.add(donation);
            }
        }
        return charityDonations;
    }
}

