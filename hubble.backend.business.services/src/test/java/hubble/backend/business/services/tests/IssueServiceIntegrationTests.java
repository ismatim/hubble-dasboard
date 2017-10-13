package hubble.backend.business.services.tests;

import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.models.IssueDto;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class IssueServiceIntegrationTests {

    @Autowired
    private IssueService issueService;

    @Autowired
    IssueRepository issueRepository;

    @Test
    public void issueService_is_instantiated() {
        assertNotNull(issueService);
    }

    @Test
    public void issueService_should_return_last_day_of_issues_by_application_id() {

        //Arrange
        //Act
        List<IssueDto> issues = issueService.getLastDay("Benchmark Home Banking");

        //Assert
        assertNotNull(issues);
    }

    @Test
    public void issueService_should_return_last_month_of_issues_by_application_id() {

        //Arrange
        //Act
        List<IssueDto> issues = issueService.getLastMonth("Benchmark Home Banking");

        //Assert
        assertNotNull(issues);
    }

}
