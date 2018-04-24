package hubble.backend.business.services.tests.integrations;

import hubble.backend.business.services.interfaces.services.kpis.KpiAveragesService;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.IssueRepository;
import hubble.backend.storage.repositories.WorkItemRepository;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class KpiAveragesServiceIntegrationTests {

    @Autowired
    private KpiAveragesService kpiService;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    WorkItemRepository workItemRepository;
    @Autowired
    AvailabilityRepository availabilityRepository;

    @Test
    public void kpiService_is_instantiated() {
        assertNotNull(kpiService);
    }

    @Test
    public void kpiAveragesService_should_return_average_by_application_id() {

        //Arrange
        //Act
        Double result = kpiService.getStandardHealthIndex("1");

        //Assert
        assertNotNull(result);
    }

}
