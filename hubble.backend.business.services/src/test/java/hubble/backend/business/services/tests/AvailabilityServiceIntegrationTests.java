package hubble.backend.business.services.tests;

import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.HubbleConstants;
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
        List<AvailabilityDto> availabilities;

        //Act
        availabilities = availabilityService.findAllAvailabilities();

        //Assert
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        Assert.assertNotNull(availabilities.get(0));
    }

    @Test
    public void availability_service_should_return_all_availabilities_by_its_id() {
        List<AvailabilityDto> availabilities = availabilityService.findAllAvailabilities();

        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (AvailabilityDto availability : availabilities) {
            assertNotNull(availabilityService.findAvailabilityById(availability.getId()));
        }

    }

    @Test
    public void availability_service_should_return_availabilities_by_application_id() {
        List<AvailabilityDto> availabilities = availabilityService.findAvailabilitiesByApplicationId("1");

        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (AvailabilityDto availability : availabilities) {
            assertEquals("1", availability.getApplicationId());
        }

    }
    
    @Test
    public void availability_service_should_return_last_10minutes_availabilities() {
        List<AvailabilityDto> availabilities = availabilityService.findLast10MinutesAvailabilities();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (AvailabilityDto availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.TEN_MINUTES)));
        }

    }

    @Test
    public void availability_service_should_return_last_hour_availabilities() {
        List<AvailabilityDto> availabilities = availabilityService.findLastHourAvailabilities();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (AvailabilityDto availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.ONE_HOUR)));
        }

    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_application_id() {
        List<AvailabilityDto> availabilities = availabilityService.findLast10MinutesAvailabilities();
        List<AvailabilityDto> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (AvailabilityDto availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.findLast10MinutesAvailabilitiesByApplicationId(availability.getApplicationId());
            for (AvailabilityDto availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.TEN_MINUTES)));
            }
        }
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_application_id() {
        List<AvailabilityDto> availabilities = availabilityService.findLastHourAvailabilities();
        List<AvailabilityDto> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (AvailabilityDto availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.findLastHourAvailabilitiesByApplicationId(availability.getApplicationId());
            for (AvailabilityDto availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.ONE_HOUR)));
            }
        }
    }
        
    @Test
    public void availability_service_should_return_last_day_availabilities() {
        List<AvailabilityDto> availabilities = availabilityService.findLastDayAvailabilities();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (AvailabilityDto availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.ONE_DAY)));
        }

    }

    @Test
    public void availability_service_should_return_last_month_availabilities() {
        List<AvailabilityDto> availabilities = availabilityService.findLastMonthAvailabilities();
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);
        for (AvailabilityDto availability : availabilities) {
            date = availability.getTimeStamp();
            assertTrue(date.after(new Date(Calendar.MONTH - HubbleConstants.ONE_MONTH)));
        }

    }

    @Test
    public void availability_service_should_return_last_day_availabilities_by_application_id() {
        List<AvailabilityDto> availabilities = availabilityService.findLastDayAvailabilities();
        List<AvailabilityDto> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (AvailabilityDto availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.findLastDayAvailabilitiesByApplicationId(availability.getApplicationId());
            for (AvailabilityDto availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MINUTE - HubbleConstants.ONE_DAY)));
            }
        }
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_by_application_id() {
        List<AvailabilityDto> availabilities = availabilityService.findLastMonthAvailabilities();
        List<AvailabilityDto> availabilitiesByApplicationID;
        Date date;
        assertTrue("Service did not find any availabilities", availabilities.size() > 0);

        for (AvailabilityDto availability : availabilities) {
            availabilitiesByApplicationID = availabilityService.findLastMonthAvailabilitiesByApplicationId(availability.getApplicationId());
            for (AvailabilityDto availabilityByApplicationId : availabilitiesByApplicationID) {
                date = availability.getTimeStamp();
                assertNotNull(availabilityByApplicationId);
                assertTrue(date.after(new Date(Calendar.MONTH - HubbleConstants.ONE_MONTH)));
            }
        }
    }
}
