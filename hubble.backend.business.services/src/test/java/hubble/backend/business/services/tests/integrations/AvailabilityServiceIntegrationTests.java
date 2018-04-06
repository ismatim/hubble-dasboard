package hubble.backend.business.services.tests.integrations;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.tests.AvailabilityHelper;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.Calendar;
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

    @Test
    public void availability_service_should_return_last_10minutes_availabilities() {
        List<Availability> availabilities = availabilityService.getLast10Minutes();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (Availability availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.TEN_MINUTES)));
        }

    }

    @Test
    public void availability_service_should_return_last_hour_availabilities() {
        List<Availability> availabilities = availabilityService.getLastHour();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (Availability availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.ONE_HOUR)));
        }

    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_application_id() {
        List<Availability> availabilities = availabilityService.getLast10Minutes();
        List<Availability> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (Availability availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.getLast10Minutes(availability.getApplicationId());
            for (Availability availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.TEN_MINUTES)));
            }
        }
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_application_id() {
        List<Availability> availabilities = availabilityService.getLastHour();
        List<Availability> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (Availability availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.getLastHour(availability.getApplicationId());
            for (Availability availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.ONE_HOUR)));
            }
        }
    }

    @Test
    public void availability_service_should_return_last_day_availabilities() {
        List<Availability> availabilities = availabilityService.getLastDay();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (Availability availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.ONE_DAY)));
        }

    }

    @Test
    public void availability_service_should_return_last_month_availabilities() {
        List<Availability> availabilities = availabilityService.getLastMonth();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (Availability availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MONTH - CalendarHelper.ONE_MONTH)));
        }

    }

    @Test
    public void availability_service_should_return_last_day_availabilities_by_application_id() {
        List<Availability> availabilities = availabilityService.getLastDay();
        List<Availability> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (Availability availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.getLastDay(availability.getApplicationId());
            for (Availability availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - CalendarHelper.ONE_DAY)));
            }
        }
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_by_application_id() {
        List<Availability> availabilities = availabilityService.getLastMonth();
        List<Availability> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (Availability availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.getLastMonth(availability.getApplicationId());
            for (Availability availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MONTH - CalendarHelper.ONE_MONTH)));
            }
        }
    }

    //TODO: Kpi
}
