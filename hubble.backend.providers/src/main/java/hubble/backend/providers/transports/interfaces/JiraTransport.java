package hubble.backend.providers.transports.interfaces;

import hubble.backend.providers.configurations.environments.JiraProviderEnvironment;
import java.util.List;
import org.json.JSONObject;

public interface JiraTransport extends Transport<JSONObject> {
	
	public JiraProviderEnvironment getEnvironment();
    
    public List<JSONObject> getAllIssuesByProject(String encodedAuthString, String project);
    
    
}
