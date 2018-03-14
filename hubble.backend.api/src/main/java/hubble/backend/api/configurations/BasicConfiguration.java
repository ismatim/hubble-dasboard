package hubble.backend.api.configurations;

import hubble.backend.api.interceptors.CommonAPIInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services", "hubble.backend.providers", "hubble.backend.storage", "hubble.backend.api"})
public class BasicConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CommonAPIInterceptor());
    }
}
