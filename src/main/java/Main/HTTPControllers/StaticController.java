package Main.HTTPControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Static class that handles routing html request to their respective html templates
 */
@Controller
public class StaticController {
    @GetMapping("/")
    public String home1() {
        return "HomePage";
    }

    @GetMapping("/HomePage")
    public String home2() {
        return "HomePage";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/Personal")
    public String personal() {
        return "admin";
    }

    @GetMapping("/submit")
    public String submit() {
        return "submit";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

}
