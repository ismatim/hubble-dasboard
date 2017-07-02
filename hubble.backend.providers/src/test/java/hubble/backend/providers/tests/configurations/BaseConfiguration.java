package hubble.backend.providers.tests.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.providers", "hubble.backend.storage"})
public class BaseConfiguration {
}
