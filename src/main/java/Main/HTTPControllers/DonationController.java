package Main.HTTPControllers;

import Main.Entities.Donation;
import Main.Managers.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles donations to charities
 */
@Controller
public class DonationController {
    @Autowired
    private DonationManager donationManager;

    @GetMapping("/payment/complete")
    public String newDonation(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "amount") String amount,
            @RequestParam(name = "charity") String charity,
            Model model
    ) {

        Donation donation = new Donation(Integer.valueOf(amount), name, charity);
        donation.upload2DB();
        donationManager.addDonation(donation);

        model.addAttribute("donationString", donation.toString());

        return "post_donate.html";
    }

}
