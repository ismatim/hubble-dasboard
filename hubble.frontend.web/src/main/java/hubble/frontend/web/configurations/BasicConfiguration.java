package hubble.frontend.web.configurations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services","hubble.backend.providers", "hubble.backend.storage", "hubble.frontend.managers"})
public class BasicConfiguration {
}
