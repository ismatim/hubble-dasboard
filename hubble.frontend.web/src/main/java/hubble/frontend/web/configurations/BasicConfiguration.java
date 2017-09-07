package hubble.frontend.web.configurations;

import hubble.frontend.business.interfaces.AvailabilityManager;
import hubble.frontend.web.interceptors.CommonModelViewInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services", "hubble.backend.providers", "hubble.backend.storage", "hubble.frontend.business"})
public class BasicConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AvailabilityManager availabilityManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CommonModelViewInterceptor(availabilityManager));

    }

}
