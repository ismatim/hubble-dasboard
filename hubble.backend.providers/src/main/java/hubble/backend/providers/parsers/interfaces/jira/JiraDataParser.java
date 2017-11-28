package hubble.backend.providers.parsers.interfaces.jira;

import org.json.JSONObject;

import hubble.backend.providers.models.jira.JiraIssuesProviderModel;

public interface JiraDataParser {
	
	public JiraIssuesProviderModel parse(JSONObject data);

}
