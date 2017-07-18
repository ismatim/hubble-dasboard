package hubble.frontend.managers.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services"})
public class BaseConfiguration {

}
