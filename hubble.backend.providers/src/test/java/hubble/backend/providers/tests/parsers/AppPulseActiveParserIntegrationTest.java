package hubble.backend.providers.tests.parsers;

import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import hubble.backend.providers.tests.configurations.BaseConfiguration;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
@ActiveProfiles("test")
public class AppPulseActiveParserIntegrationTest extends AppPulseBaseUnitTests {

    @Autowired
    private AppPulseActiveParser appPulseActiveParser;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Test
    public void AppPulseActiveParser_should_be_instantiated() {
        assertNotNull(appPulseActiveParser);
    }

    @Test
    public void AppPulseActiveParser_when_it_runs_should_connect_get_data_and_save_it() {

        //Assign
        availabilityRepository.deleteAll();

        //Act
        appPulseActiveParser.run();

        //Assert
        List<AvailabilityStorage> availabilities = appPulseActiveParser.getAvailabilitiesStorage();
        assertNotNull(availabilities);
        assertTrue(availabilities.stream().allMatch((availabilityFromAppPulse) -> {
            return availabilityRepository.exist(availabilityFromAppPulse);
        }));
//
//        availabilities.stream().forEach((availabilityFromAppPulse) -> {
//            availabilityRepository.delete(availabilityFromAppPulse);
//        });
    }
}
