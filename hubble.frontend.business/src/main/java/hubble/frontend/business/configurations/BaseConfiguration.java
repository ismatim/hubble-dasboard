package hubble.frontend.business.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services", "hubble.frontend"})
public class BaseConfiguration {

}
