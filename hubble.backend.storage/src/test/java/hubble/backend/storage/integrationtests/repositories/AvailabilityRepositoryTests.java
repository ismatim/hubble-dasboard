package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Providers;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.configurations.BaseConfiguration;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.tests.AvailabilityHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class AvailabilityRepositoryTests {

    @Autowired
    AvailabilityRepository availabilityRepository;
    AvailabilityHelper helper = new AvailabilityHelper();

    @Before
    public void prepareTestEnvironment() {
    }

    @Test
    public void AvailabilityRepository_should_be_instantiated() {

        assertNotNull(availabilityRepository);
    }

    @Test
    public void AvailabilityRepository_should_be_able_to_save_records() {

        AvailabilityStorage availabilityStorage = helper.getFakeAvailabilityStorage();

        //Act
        availabilityStorage = availabilityRepository.insert(availabilityStorage);

        //Assert
        assertNotNull(availabilityStorage.getId());
        availabilityRepository.delete(availabilityStorage.getId());
    }

    @Test
    public void AvailabilityRepository_should_be_able_to_retrieve_last_10_minutes_availabilities() {

        //Assign
        int duration = 10;
        availabilityRepository.deleteAll();
        List<AvailabilityStorage> availabilityStorages = helper.getFakeListOfAvailabilityStorage();

        Date now = new Date();
        Calendar nowCalendar = CalendarHelper.getNow();
        nowCalendar.add(Calendar.MINUTE, -duration);

        availabilityStorages.forEach((availabilityStorage) -> {
            availabilityStorage.setTimeStamp(now);
        });

        availabilityRepository.save(availabilityStorages);

        //Act
        availabilityStorages = availabilityRepository.findAvailabilitiesByDurationMinutes(duration, Providers.PROVIDERS_NAME.APP_PULSE);

        //Assert
        assertNotNull(availabilityStorages.size() > 0);
        assertTrue(availabilityStorages.stream().allMatch((a) -> {
            return nowCalendar.getTime().before(a.getTimeStamp());
        }));

    }

}
