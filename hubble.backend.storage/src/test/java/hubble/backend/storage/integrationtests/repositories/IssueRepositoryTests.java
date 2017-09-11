package hubble.backend.storage.integrationtests.repositories;

import hubble.backend.storage.configurations.StorageComponentConfiguration;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.IssueRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StorageComponentConfiguration.class)
public class IssueRepositoryTests {
    @Autowired
    IssueRepository issueRepository;
    StorageTestsHelper helper = new StorageTestsHelper();
    
    @Test
    public void IssueRepository_should_be_instantiated() {
        assertNotNull(issueRepository);
    }
    
    @Test
    public void issue_repository_should_return_all_samples_by_its_id() {
        List<IssueStorage> samples = issueRepository.findAll();
        for (IssueStorage sample : samples) {
            assertNotNull(issueRepository.findOne(sample.getId()));
        }
    }
    
    @Test
    public void AvailabilityRepository_should_be_able_to_save_records() {

        IssueStorage issueStorage = helper.getFakeIssueStorage();

        //Act
        issueStorage = issueRepository.insert(issueStorage);

        //Assert
        assertNotNull(issueStorage.getId());
        issueRepository.delete(issueStorage.getId());
    }
    
     @Test
    public void IssueRepository_should_be_able_to_check_if_availability_exist() {
        //Assign
        String providerNameFake = "provider-name-fake";
        issueRepository.deleteAll();
        List<IssueStorage> issueStorages = helper.getFakeListOfIssueStorage();

        Date now = new Date();

        issueStorages.forEach((issueStorage) -> {
            issueStorage.setProviderName(providerNameFake);
            issueStorage.setRegisteredDate(now);
        });

        issueRepository.save(issueStorages);
        IssueStorage issueToLook = issueStorages.get(0);

        //Act
        boolean result = issueRepository.exist(issueToLook);

        //Assert
        assertTrue(result);
        issueRepository.deleteAll();
    }
    
    @Test
    public void IssueRepository_should_be_able_to_check_if_issue_not_exist_if_providerorigin_not_match() {
        //Assign
        String providerNameFake = "provider-name-fake";
        issueRepository.deleteAll();
        List<IssueStorage> issueStorages = helper.getFakeListOfIssueStorage();

        Date now = new Date();

        issueStorages.forEach((issueStorage) -> {
            issueStorage.setProviderName(providerNameFake);
            issueStorage.setRegisteredDate(now);
        });

        issueRepository.save(issueStorages);
        IssueStorage issueToLook = issueStorages.get(0);

        issueToLook.setProviderName(EMPTY);

        //Act
        boolean result = issueRepository.exist(issueToLook);

        //Assert
        assertFalse(result);
        issueRepository.deleteAll();
    }
    
    @Test
    public void IssueRepository_should_return_issues_by_application_id_between_dates() {
        String applicationId="Benchmark Home Banking";
        Date startDate = new GregorianCalendar(2017, Calendar.SEPTEMBER, 6).getTime();
        Date endDate = new GregorianCalendar(2017, Calendar.SEPTEMBER, 7    ).getTime();
        issueRepository.deleteAll();
        List<IssueStorage> issueStorages = helper.getFakeListOfIssueStorage();
        
        issueRepository.save(issueStorages);
        List<IssueStorage> issuesRetrieved = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, startDate, endDate);
        assertTrue(issueStorages.size()>0);
        assertTrue(issuesRetrieved.size()==2);
        assertEquals(2, issuesRetrieved.get(0).getExternalId());
        assertEquals(3, issuesRetrieved.get(1).getExternalId());
    }
}
