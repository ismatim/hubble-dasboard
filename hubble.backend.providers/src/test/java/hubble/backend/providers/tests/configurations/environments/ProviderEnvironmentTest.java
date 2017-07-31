package hubble.backend.providers.tests.configurations.environments;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import hubble.backend.providers.configurations.environments.ProviderEnvironment;
import hubble.backend.providers.tests.configurations.BaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class ProviderEnvironmentTest {

    @Autowired
    private ProviderEnvironment environment;
    @Test
    public void ProviderEnvironment_should_get_url() {

        assertNotNull(environment);
        assertNotNull(environment.getUrl());
    }
}
