package Main.Managers;

import Main.Entities.Charity;
import Main.Entities.SqlDriver;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Singleton charity object manager. Should be instantiated using Spring Beans
 */
@Component
public class CharityManager {

    private HashMap<String, Charity> charities;

    private CharityManager() {
        System.out.println("Creating new charity manager");
        charities = new HashMap<>();
        for (Charity charity : SqlDriver.selectAllCharity()) {
            charities.put(charity.getUser(), charity);
        }
    }


    public HashMap<String, Charity> getCharities() {
        return charities;
    }

    public void addDonation(Charity charity) {
        this.charities.put(charity.getUser(), charity);
    }

    public Charity getCharity(String name) {
        return charities.get(name);
    }
}
