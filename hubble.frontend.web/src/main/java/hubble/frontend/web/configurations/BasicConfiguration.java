package hubble.frontend.web.configurations;

import hubble.frontend.managers.fake_implementations.ApplicationManagerFakeImpl;
import hubble.frontend.managers.fake_implementations.AvailabilityManagerFakeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import hubble.frontend.managers.interfaces.ApplicationManager;
import hubble.frontend.managers.interfaces.AvailabilityManager;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services", "hubble.backend.providers", "hubble.backend.storage"})
public class BasicConfiguration {

    @Bean
    public ApplicationManager appPulseApplicationManager() {
        return new ApplicationManagerFakeImpl();
    }

    @Bean
    public AvailabilityManager appPulseAvailabilityManager() {
        return new AvailabilityManagerFakeImpl();
    }

}
