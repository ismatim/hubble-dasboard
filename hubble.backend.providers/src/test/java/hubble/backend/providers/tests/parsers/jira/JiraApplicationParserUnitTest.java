package hubble.backend.providers.tests.parsers.jira;

import hubble.backend.providers.models.jira.JiraIssuesProviderModel;
import hubble.backend.providers.parsers.implementations.jira.JiraDataParserImpl;
import hubble.backend.providers.tests.JiraBaseUnitTest;
import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JiraApplicationParserUnitTest extends JiraBaseUnitTest {

    @Test
    public void jira_parser_should_map_json_to_issue_model() {
        //Assign
        JiraDataParserImpl jiraParser = new JiraDataParserImpl();
        JiraIssuesProviderModel jiraModel;
        JSONObject fakeResponse = loadFakeResponse();

        //Act
        jiraModel = jiraParser.extract(fakeResponse);

        //Assert
        assertNotNull(jiraModel);
    }
}
