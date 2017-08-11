package hubble.frontend.managers.tests;

import hubble.backend.providers.parsers.interfaces.apppulse.AppPulseActiveApplicationsParser;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class PerformanceManagerIntegrationTest {
    @Autowired
    PerformanceManager performanceManager;
    @Autowired
    private AppPulseActiveApplicationsParser appPulseActiveParser;
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Test
    public void performance_manager_should_be_instantiated() {
        //Assert
        assertNotNull(performanceManager);
    }
}
