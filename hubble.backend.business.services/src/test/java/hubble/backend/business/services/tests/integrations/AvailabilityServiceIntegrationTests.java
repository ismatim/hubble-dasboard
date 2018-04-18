package hubble.backend.business.services.tests.integrations;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.tests.AvailabilityHelper;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class AvailabilityServiceIntegrationTests {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    AvailabilityRepository availabilityRepository;
    AvailabilityHelper helper = new AvailabilityHelper();

    private static List<AvailabilityStorage> availabilityStorages;

    @Before
    public void SetUpTests() {
        String providerOriginFake = "AppPulse Active";
        availabilityStorages = helper.getFakeListOfAvailabilityStorage();
        Date now = new Date();

        availabilityStorages.forEach((availabilityStorage) -> {
            availabilityStorage.setProviderOrigin(providerOriginFake);
            availabilityStorage.setTimeStamp(now);
        });

        availabilityRepository.save(availabilityStorages);

    }

    @After
    public void after() {
        availabilityRepository.deleteAll();
    }

    @Test
    public void availability_service_is_instantiated() {
        assertNotNull(availabilityService);
    }

    @Test
    public void availability_service_should_be_able_to_retrieve_at_least_one_availability() {

        //Assign
        List<Availability> availabilities;

        //Act
        availabilities = availabilityService.getAll();

        //Assert
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        Assert.assertNotNull(availabilities.get(0));
    }

    @Test
    public void availability_service_should_return_all_availabilities_by_its_id() {
        List<Availability> availabilities = availabilityService.getAll();

        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (Availability availability : availabilities) {
            assertNotNull(availabilityService.get(availability.getId()));
        }

    }

    @Test
    public void availability_service_should_return_availabilities_by_application_id() {
        List<Availability> availabilities = availabilityService.getAll("fake-app-example-1");

        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (Availability availability : availabilities) {
            assertEquals("1", availability.getApplicationId());
        }

    }

    //TODO: Kpi
}
