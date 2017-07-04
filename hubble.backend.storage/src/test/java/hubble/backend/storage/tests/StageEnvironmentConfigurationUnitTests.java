package hubble.backend.storage.tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import hubble.backend.storage.configurations.environment.StorageEnvironment;

@ActiveProfiles("stage")
@RunWith(SpringJUnit4ClassRunner.class)
public class StageEnvironmentConfigurationUnitTests {

    @Autowired
    public Environment env;
    @Autowired
    public ConfigurableEnvironment confEnv;
    @Autowired
    public StorageEnvironment storageConfiguration;

    @Test
    public void EnvironmentConfiguration_getting_properties_for_test_env_are_expected() {

        //Assert
        assertTrue(storageConfiguration.getHost().equals("localhost"));
        assertTrue(storageConfiguration.getDbname().equals("hubble-stage"));
    }
}
