package hubble.backend.providers.tests.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan(basePackages = {"hubble.backend.providers", "hubble.backend.storage"})
public class BaseConfiguration {
}
