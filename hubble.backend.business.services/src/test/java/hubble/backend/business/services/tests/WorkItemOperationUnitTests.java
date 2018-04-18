package hubble.backend.business.services.tests;

import hubble.backend.business.services.implementations.operations.WorkItemOperationsImpl;
import hubble.backend.business.services.models.measures.quantities.WorkItemQuantity;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.models.WorkItemStorage;
import hubble.backend.storage.repositories.WorkItemRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
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
public class WorkItemOperationUnitTests {

    @Mock
    private WorkItemRepository workItemRepository;
    @InjectMocks
    private WorkItemOperationsImpl workItemOperation;

    private List<WorkItemStorage> workItemStorageList;
    private StorageTestsHelper storageHelper = new StorageTestsHelper();

    @Before
    public void Before() {
        workItemStorageList = new ArrayList();
    }

    @Test
    public void workItem_operation_should_be_instantiated() {
        assertNotNull(workItemOperation);
    }

    @Test
    public void workItem_repository_should_be_instantiated() {
        assertNotNull(workItemRepository);
    }

    @Test
    public void workItem_operation_should_calculateWorkItemQuantityLastMonth() {
        String applicationId = "1";
        workItemStorageList = storageHelper.getFakeListOfWorkItemStorage();
        when(workItemRepository.findWorkItemsByApplicationIdBetweenDates(eq(applicationId), anyObject(), anyObject())).thenReturn(workItemStorageList);

        WorkItemQuantity workItemQuantity = workItemOperation.calculateWorkItemQuantityLastMonth(applicationId);

        assertEquals(4, (long) workItemQuantity.getQuantity());
    }

    @Test
    public void workItem_operation_should_calculateWorkItemQuantityLastDay() {
        String applicationId = "1";
        workItemStorageList = storageHelper.getFakeListOfWorkItemStorage();
        when(workItemRepository.findWorkItemsByApplicationIdBetweenDates(eq(applicationId), anyObject(), anyObject())).thenReturn(workItemStorageList);

        WorkItemQuantity workItemQuantity = workItemOperation.calculateWorkItemQuantityLastWeek(applicationId);

        assertEquals(4, (long) workItemQuantity.getQuantity());
    }
}
