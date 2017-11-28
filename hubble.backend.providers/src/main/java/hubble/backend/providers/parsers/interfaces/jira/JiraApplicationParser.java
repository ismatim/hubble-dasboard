package hubble.backend.providers.parsers.interfaces.jira;

import java.util.List;
import org.json.JSONObject;

import hubble.backend.providers.models.jira.JiraApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.ApplicationStorage;

public interface JiraApplicationParser extends Parser {
	
	public JiraApplicationProviderModel parse(JSONObject data);
	
	public List<ApplicationStorage> getApplicationStorage(); //Testing purpose
}
