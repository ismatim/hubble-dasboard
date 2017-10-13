package hubble.backend.business.services.tests;

import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.interfaces.services.WorkItemService;
import hubble.backend.business.services.models.IssueDto;
import hubble.backend.business.services.models.WorkItemDto;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.repositories.IssueRepository;
import hubble.backend.storage.repositories.WorkItemRepository;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class WorkItemServiceIntegrationTests {

    @Autowired
    private WorkItemService workItemService;

    @Autowired
    WorkItemRepository workItemRepository;

    @Test
    public void workItemService_is_instantiated() {
        assertNotNull(workItemService);
    }

    @Test
    public void workItemService_should_return_last_day_of_issues_by_application_id() {

        //Arrange
        //Act
        List<WorkItemDto> workItems = workItemService.getLastDay("Benchmark Home Banking");

        //Assert
        assertNotNull(workItems);
    }

    @Test
    public void workItemService_should_return_last_month_of_issues_by_application_id() {

        //Arrange
        //Act
        List<WorkItemDto> workItems = workItemService.getLastMonth("Benchmark Home Banking");

        //Assert
        assertNotNull(workItems);
    }

}
