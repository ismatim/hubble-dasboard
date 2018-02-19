package hubble.backend.providers.parsers.interfaces.jira;

import org.json.JSONObject;

import hubble.backend.providers.models.jira.JiraIssuesProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;

public interface JiraDataParser extends Parser {
	
	public JiraIssuesProviderModel parse(JSONObject data);

}
