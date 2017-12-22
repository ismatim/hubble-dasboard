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

import hubble.backend.providers.configurations.JiraConfiguration;
import hubble.backend.providers.configurations.environments.JiraProviderEnvironment;
import hubble.backend.providers.transports.interfaces.JiraTransport;

@Component
public class JiraTransportImpl implements JiraTransport {

    @Autowired
    JiraProviderEnvironment environment;

    @Autowired
    JiraConfiguration configuration;

    private final Logger logger = LoggerFactory.getLogger(JiraTransportImpl.class);

    @Override
    public JSONObject getData() {
            return null;
    }

    @Override
    public JiraProviderEnvironment getEnvironment() {
            return this.environment;
    }

    @Override
    public JiraConfiguration getConfiguration() {
            return this.configuration;
    }

    @Override
    public JSONObject getAllIssuesByProject(String encodedAuthString, String projectKey) {
        String path=String.format("/rest/api/2/search?jql=project=%s", projectKey);
        String requestsUri = buildUri(path);
        HttpResponse<JsonNode> response;
        JSONObject data;

        try {
            response = Unirest.get(requestsUri)
                    .header("Authorization","Basic " + encodedAuthString)
                    .asJson();
        } catch (UnirestException e) {
            logger.error(e.getMessage());
            return null;
        }

        data = response.getBody().getObject();

        if (data == null) {
                logger.info("La respuesta para el request enviado es nula");
                return null;
        }

        return data;
    }
	
	 private String buildUri(String path){
         String uri = String.format("http://%s:%s%s"
                 ,environment.getHost()
                 ,environment.getPort()
                 ,path);

     return uri;
 }
}
