package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.configurations.StorageComponentConfiguration;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.tests.AvailabilityHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StorageComponentConfiguration.class)
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
    public void availability_repository_should_return_all_samples_by_its_id() {
        List<AvailabilityStorage> samples = availabilityRepository.findAll();

        for (AvailabilityStorage sample : samples) {

            assertNotNull(availabilityRepository.findOne(sample.getId()));
        }

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
            availabilityStorage.setTimeStamp(nowCalendar.getTime());
        });

        availabilityRepository.save(availabilityStorages);

        //Act
        availabilityStorages = availabilityRepository.findAvailabilitiesByDurationMinutes(duration);

        //Assert
        assertNotNull(availabilityStorages.size() > 0);
        assertTrue(availabilityStorages.stream().allMatch((a) -> {
            return nowCalendar.getTime().after(a.getTimeStamp()) && now.before(a.getTimeStamp());
        }));

    }

    @Test
    public void AvailabilityRepository_should_be_able_to_check_if_availability_exist() {
        //Assign
        String providerOriginFake = "provider-origin-fake";
        availabilityRepository.deleteAll();
        List<AvailabilityStorage> availabilityStorages = helper.getFakeListOfAvailabilityStorage();

        Date now = new Date();

        availabilityStorages.forEach((availabilityStorage) -> {
            availabilityStorage.setProviderOrigin(providerOriginFake);
            availabilityStorage.setTimeStamp(now);
        });

        availabilityRepository.save(availabilityStorages);
        AvailabilityStorage availabilityToLook = availabilityStorages.get(0);

        //Act
        boolean result = availabilityRepository.exist(availabilityToLook);

        //Assert
        assertTrue(result);
    }

    @Test
    public void AvailabilityRepository_should_be_able_to_check_if_availability_not_exist_if_timestamp_not_match() {
        //Assign
        String providerOriginFake = "provider-origin-fake";
        availabilityRepository.deleteAll();
        List<AvailabilityStorage> availabilityStorages = helper.getFakeListOfAvailabilityStorage();

        Date now = new Date();

        availabilityStorages.forEach((availabilityStorage) -> {
            availabilityStorage.setProviderOrigin(providerOriginFake);
            availabilityStorage.setTimeStamp(now);
        });

        Calendar nowCalendar = CalendarHelper.getNow();
        nowCalendar.add(Calendar.HOUR, -10);

        availabilityRepository.save(availabilityStorages);
        AvailabilityStorage availabilityToLook = availabilityStorages.get(0);

        availabilityToLook.setTimeStamp(nowCalendar.getTime());

        //Act
        boolean result = availabilityRepository.exist(availabilityToLook);

        //Assert
        assertFalse(result);
    }

    @Test
    public void AvailabilityRepository_should_be_able_to_check_if_availability_not_exist_if_providerorigin_not_match() {
        //Assign
        String providerOriginFake = "provider-origin-fake";
        availabilityRepository.deleteAll();
        List<AvailabilityStorage> availabilityStorages = helper.getFakeListOfAvailabilityStorage();

        Date now = new Date();

        availabilityStorages.forEach((availabilityStorage) -> {
            availabilityStorage.setProviderOrigin(providerOriginFake);
            availabilityStorage.setTimeStamp(now);
        });

        availabilityRepository.save(availabilityStorages);
        AvailabilityStorage availabilityToLook = availabilityStorages.get(0);

        availabilityToLook.setProviderOrigin(EMPTY);

        //Act
        boolean result = availabilityRepository.exist(availabilityToLook);

        //Assert
        assertFalse(result);
    }

    @Test
    public void availability_repository_should_return_samples_by_application_id() {
        List<AvailabilityStorage> samples = availabilityRepository.findAvailabilitiesByApplicationId("1");

        System.out.println(samples.size());
        for (AvailabilityStorage sample : samples) {

            assertEquals("1", sample.getApplicationId());
        }

    }

    @Test
    public void availability_repository_should_return_last_10minutes_samples_by_application_id() {
        List<AvailabilityStorage> samples = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, "1");

        System.out.println(samples.size());
        for (AvailabilityStorage sample : samples) {

            assertEquals("1", sample.getApplicationId());
        }

    }

    @Test
    public void availability_repository_should_return_last_hour_samples_by_application_id() {
        List<AvailabilityStorage> samples = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, "1");

        System.out.println(samples.size());
        for (AvailabilityStorage sample : samples) {

            assertEquals("1", sample.getApplicationId());
        }
    }
}
