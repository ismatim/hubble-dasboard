package hubble.backend.tests.configurations;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.providers"})
public class BaseConfiguration {

}
