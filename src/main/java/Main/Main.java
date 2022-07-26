package Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Top level container
 */
@SpringBootApplication
@RestController
public class Main {

    @Autowired
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());


        try {
            SpringApplication.run(Main.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
