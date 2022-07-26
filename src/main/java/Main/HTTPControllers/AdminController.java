package Main.HTTPControllers;

import Main.Entities.Charity;
import Main.Entities.CharityPayment;
import Main.Entities.SqlDriver;
import Main.Managers.CharityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    CharityManager charityManager;

    @GetMapping("/admin")
    public String admin(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Charity charity = charityManager.getCharity(user.getUsername());
        System.out.println("Getting request to access admin panel by charity:" + charity.getName());
        System.out.println("Accessing updates:" + charity.getName());
        List<CharityPayment> charityPayments = SqlDriver.selectCharitySpending(charity.getName());
        System.out.println(charityPayments.size());
        model.addAttribute("donations", SqlDriver.selectCharitySpending(charity.getName()));

        return "admin";
    }

    @GetMapping("/admin/update")
    public String newDonation(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "amount") String amount,
            @RequestParam(name = "description") String description,
            Model model
    ) {

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Charity charity = charityManager.getCharity(user.getUsername());

        System.out.println("Getting Request to update charity");
        try {
            SqlDriver.updateCharitySpending(id, Integer.parseInt(amount), description, charity.getName());
        } catch (Exception e) {
            System.out.println(e);
        }
        return "admin";
    }
}
