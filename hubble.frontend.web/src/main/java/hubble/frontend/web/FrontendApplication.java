package hubble.frontend.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class FrontendApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FrontendApplication.class, args);

    }
}
