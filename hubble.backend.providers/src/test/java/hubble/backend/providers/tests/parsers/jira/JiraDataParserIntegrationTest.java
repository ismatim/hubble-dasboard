package hubble.backend.providers.tests.parsers.jira;

import hubble.backend.providers.configurations.ProvidersConfiguration;
import hubble.backend.providers.parsers.interfaces.jira.JiraDataParser;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProvidersConfiguration.class)
public class JiraDataParserIntegrationTest {
    
    @Autowired
    private JiraDataParser jiraDataParser;
    @Autowired
    private IssueRepository issueRepository;
    
    @Test
    public void jira_should_parse_and_save_data_to_issue_repository() {
        issueRepository.deleteAll();
        
        jiraDataParser.run();
        List<IssueStorage> issuesStorage = issueRepository.findAll();
        
        assertTrue(issuesStorage.size() > 0);
        issuesStorage.forEach((issue) -> {
            assertTrue(issueRepository.exist(issue));
        }); 
    }
}
