package Main.HTTPControllers;

import Main.Managers.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles returning donations for view when requested
 */
@Controller
public class GetDonations {
    // Allow Spring to handle Singleton Instance
    @Autowired
    private DonationManager donationManager;

    @GetMapping("/donation")
    public String getDonations(Model model) {
        model.addAttribute("nature", donationManager.getCharityDonations("nature"));
        model.addAttribute("disabled", donationManager.getCharityDonations("disabled"));
        model.addAttribute("elderly", donationManager.getCharityDonations("elderly"));
        model.addAttribute("disaster", donationManager.getCharityDonations("disaster"));
        System.out.println(donationManager.getDonations().size());
        return "donation";
    }
}
