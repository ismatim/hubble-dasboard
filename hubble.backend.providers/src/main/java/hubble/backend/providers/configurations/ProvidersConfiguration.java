package hubble.backend.providers.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"hubble.backend.providers", "hubble.backend.storage","hubble.backend.core"})
public class ProvidersConfiguration {
}
