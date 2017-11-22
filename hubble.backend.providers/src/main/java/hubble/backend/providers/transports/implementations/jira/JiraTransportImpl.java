package hubble.backend.providers.transports.implementations.jira;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import hubble.backend.providers.configurations.environments.JiraProviderEnvironment;
import hubble.backend.providers.transports.interfaces.JiraTransport;

@Component
public class JiraTransportImpl implements JiraTransport {

	@Autowired
	JiraProviderEnvironment environment;
	
	private final Logger logger = LoggerFactory.getLogger(JiraTransportImpl.class);
	
	@Override
	public JSONObject getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JiraProviderEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		return this.environment;
	}

	@Override
	public List<JSONObject> getAllIssuesByProject(String encodedAuthString, String project) {
		// TODO Auto-generated method stub
		String path=String.format("/rest/api/2/search?jql=project=\"%s\"", project);
        String requestsUri = buildUri(path);
        HttpResponse<JsonNode> response = null;
        JSONArray jsonArray = null;
        List<JSONObject> dataList = new ArrayList<JSONObject>();
        
        try {
            response = Unirest.get(requestsUri)
                    .header("Authorization","Basic "+encodedAuthString)
                    .asJson();
        } catch (UnirestException e) {
            logger.error(e.getMessage());
            return null;
        }
        
        jsonArray = response.getBody().getObject().getJSONObject("ns2:requestTypes").getJSONArray("requestType");

	}
	
	 private String buildUri(String path){
         String uri = String.format("http://%s:%s%s"
                 ,environment.getHost()
                 ,environment.getPort()
                 ,path);

     return uri;
 }
}
