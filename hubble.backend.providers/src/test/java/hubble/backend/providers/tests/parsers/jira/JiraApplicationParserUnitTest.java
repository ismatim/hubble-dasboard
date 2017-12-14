package hubble.backend.providers.tests.parsers.jira;

import hubble.backend.providers.models.jira.JiraApplicationProviderModel;
import hubble.backend.providers.parsers.implementations.jira.JiraApplicationParserImpl;
import hubble.backend.providers.tests.JiraBaseUnitTest;
import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JiraApplicationParserUnitTest extends JiraBaseUnitTest {
    
    @Test
    public void jira_parser_should_map_issue_to_application_model() {
        //Assign
        JiraApplicationParserImpl jiraParser = new JiraApplicationParserImpl();
        JiraApplicationProviderModel jiraModel;
        JSONObject fakeResponse = loadFakeResponse();
        
        //Act
        jiraModel = jiraParser.extract(fakeResponse);
       
        //Assert
        assertNotNull(jiraModel);
    }
}
