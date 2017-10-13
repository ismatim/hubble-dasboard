package hubble.backend.business.services.tests;

import hubble.backend.business.services.implementations.operations.IssueOperationsImpl;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class IssueOperationUnitTests {

    @Mock
    private IssueRepository issueRepository;
    @InjectMocks
    private IssueOperationsImpl issueOperation;

    private List<IssueStorage> issueStorageList;
    private StorageTestsHelper storageHelper = new StorageTestsHelper();

    @Before
    public void Before() {
        issueStorageList = new ArrayList();
    }

    @Test
    public void issue_operation_should_be_instantiated(){
        assertNotNull(issueOperation);
    }

    @Test
    public void issue_repository_should_be_instantiated(){
        assertNotNull(issueRepository);
    }

    @Test
    public void issue_operation_should_calculateIssueQuantityLastMonth(){
        String applicationId="1";
        issueStorageList = storageHelper.getFakeListOfIssueStorage();
        when(issueRepository.findIssuesByApplicationIdBetweenDates(eq(applicationId),anyObject(),anyObject())).thenReturn(issueStorageList);

        IssuesQuantity issuesQuantity = issueOperation.calculateIssuesQuantityLastMonth(applicationId);

        assertEquals(5, (long)issuesQuantity.getQuantity());
    }

    @Test
    public void issue_operation_should_calculateIssueQuantityLastDay(){
        String applicationId="1";
        issueStorageList = storageHelper.getFakeListOfIssueStorage();
        when(issueRepository.findIssuesByApplicationIdBetweenDates(eq(applicationId),anyObject(),anyObject())).thenReturn(issueStorageList);

        IssuesQuantity issuesQuantity = issueOperation.calculateIssuesQuantityLastDay(applicationId);

        assertEquals(5, (long)issuesQuantity.getQuantity());
    }
}
