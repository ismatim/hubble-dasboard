package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.storage.configurations.StorageComponentConfiguration;
import hubble.backend.storage.models.WorkItemStorage;
import hubble.backend.storage.tests.StorageTestsHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import hubble.backend.storage.repositories.WorkItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StorageComponentConfiguration.class)
public class WorkItemRepositoryTests {

    @Autowired
    WorkItemRepository workItemRepository;
    StorageTestsHelper helper = new StorageTestsHelper();

    @Test
    public void workItem_repository_should_be_instantiated(){
        assertNotNull(workItemRepository);
    }

    @Test
    public void workItem_repository_should_return_all_workItems_by_it_ids(){
        List<WorkItemStorage> samples = workItemRepository.findAll();
        for (WorkItemStorage sample : samples) {
            assertNotNull(workItemRepository.findOne(sample.getId()));
        }
    }

    @Test
    public void WorkItemRepository_should_be_able_to_save_records() {

        WorkItemStorage workItemStorage = helper.getFakeWorkItemStorage();

        //Act
        workItemStorage = workItemRepository.insert(workItemStorage);

        //Assert
        assertNotNull(workItemStorage.getId());
        workItemRepository.delete(workItemStorage.getId());
    }

    @Test
    public void WorkItemRepository_should_be_able_to_check_if_workItem_exists() {
        //Assign
        String providerNameFake = "provider-name-fake";
        workItemRepository.deleteAll();
        List<WorkItemStorage> workItemStorages = helper.getFakeListOfWorkItemStorage();

        Date now = new Date();

        workItemStorages.forEach((workItemStorage) -> {
            workItemStorage.setProviderName(providerNameFake);
            workItemStorage.setRegisteredDate(now);
        });

        workItemRepository.save(workItemStorages);
        WorkItemStorage workItemToLook = workItemStorages.get(0);

        //Act
        boolean result = workItemRepository.exist(workItemToLook);

        //Assert
        assertTrue(result);
        workItemRepository.deleteAll();
    }

    @Test
    public void WorkItemRepository_should_be_able_to_check_if_workItem_not_exists_if_providerorigin_not_match() {
        //Assign
        String providerNameFake = "provider-name-fake";
        workItemRepository.deleteAll();
        List<WorkItemStorage> workItemStorages = helper.getFakeListOfWorkItemStorage();

        Date now = new Date();

        workItemStorages.forEach((workItem) -> {
            workItem.setProviderName(providerNameFake);
            workItem.setRegisteredDate(now);
        });

        workItemRepository.save(workItemStorages);
        WorkItemStorage workItemToLook = workItemStorages.get(0);

        workItemToLook.setProviderName(EMPTY);

        //Act
        boolean result = workItemRepository.exist(workItemToLook);

        //Assert
        assertFalse(result);
        workItemRepository.deleteAll();
    }

    @Test
    public void WorkItemRepository_should_return_workItems_by_application_id_between_dates() {
        String applicationId="Benchmark Home Banking";
        Date startDate = new GregorianCalendar(2017, Calendar.SEPTEMBER, 6).getTime();
        Date endDate = new GregorianCalendar(2017, Calendar.SEPTEMBER, 7).getTime();
        workItemRepository.deleteAll();
        List<WorkItemStorage> workItemStorages = helper.getFakeListOfWorkItemStorage();

        workItemRepository.save(workItemStorages);
        List<WorkItemStorage> workItemsRetrieved = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, startDate, endDate);
        assertTrue(workItemStorages.size()>0);
        assertEquals(2, workItemsRetrieved.size());
        assertEquals(2, workItemsRetrieved.get(0).getExternalId());
        assertEquals(3, workItemsRetrieved.get(1).getExternalId());
    }
}
