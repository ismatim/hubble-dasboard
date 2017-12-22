package hubble.backend.providers.transports.interfaces;

import hubble.backend.providers.configurations.JiraConfiguration;
import hubble.backend.providers.configurations.environments.JiraProviderEnvironment;
import org.json.JSONObject;

public interface JiraTransport extends Transport<JSONObject> {
	
    public JiraProviderEnvironment getEnvironment();
    
    public JiraConfiguration getConfiguration();
	
    public JSONObject getAllIssuesByProject(String encodedAuthString, String project); 
}
