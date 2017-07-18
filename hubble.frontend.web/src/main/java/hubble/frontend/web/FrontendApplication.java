package hubble.frontend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import hubble.frontend.managers.interfaces.AvailabilityManager;

@SpringBootApplication
@ComponentScan(basePackages = {"hubble.frontend"})
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
