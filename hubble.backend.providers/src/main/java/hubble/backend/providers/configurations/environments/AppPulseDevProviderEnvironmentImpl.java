package hubble.backend.providers.configurations.environments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile({"default"})
@PropertySource("classpath:config/apppulse.properties")
public class AppPulseDevProviderEnvironmentImpl implements ProviderEnvironment {

    @Value("${app.url}")
    private String url;

    @Value("${app.client}")
    private String client;

    @Value("${app.secret}")
    private String secret;

    @Override
    public String getClient() {
        return client;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getUrl() {
        return url;
    }

}
