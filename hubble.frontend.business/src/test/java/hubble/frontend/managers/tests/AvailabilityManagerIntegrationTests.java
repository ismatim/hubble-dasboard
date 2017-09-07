package hubble.frontend.managers.tests;

import hubble.frontend.business.interfaces.AvailabilityManager;
import hubble.frontend.business.models.Availability;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class AvailabilityManagerIntegrationTests {

    @Autowired
    AvailabilityManager availabilityManager;

    @Test
    public void availability_manager_should_be_instantiated() {
        //Assert
        assertNotNull(availabilityManager);
    }

    @Test
    public void availability_Manager_should_return_all_availabilities_from_db() {
        //Act
        List<Availability> availabilities = availabilityManager.getAllAvailabilities();

        //Assert
        assertNotNull(availabilities.size());
    }
}
