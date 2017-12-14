package hubble.backend.providers.parsers.implementations.jira;

import com.fasterxml.jackson.databind.ObjectMapper;
import hubble.backend.providers.configurations.mappers.jira.JiraMapperConfiguration;
import hubble.backend.providers.models.jira.JiraApplicationProviderModel;
import hubble.backend.providers.parsers.implementations.alm.AlmApplicationParserImpl;
import hubble.backend.providers.parsers.interfaces.jira.JiraApplicationParser;
import hubble.backend.providers.transports.interfaces.JiraTransport;
import hubble.backend.storage.models.ApplicationStorage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JiraApplicationParserImpl implements JiraApplicationParser {
    
    @Autowired
    private JiraTransport jiraTransport;
    @Autowired
    private JiraMapperConfiguration jiraMapperConfiguration;
    
    private final Logger logger = LoggerFactory.getLogger(JiraApplicationParserImpl.class);
    
    @Override
    public JiraApplicationProviderModel parse(JSONObject data) {
        if (data == null) {
            return null;
        }
       
        JiraApplicationProviderModel jiraModel = this.extract(data); 
        
        return jiraModel;
    }

    @Override
    public List<ApplicationStorage> getApplicationStorage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public JiraApplicationProviderModel extract(JSONObject data) {
        byte[] dataBytes = data.toString().getBytes();
        InputStream dataStream = new ByteArrayInputStream(dataBytes);
        
        ObjectMapper objMapper = new ObjectMapper();
        JiraApplicationProviderModel jiraModel;
        
        try {
            jiraModel = objMapper.readValue(dataStream, JiraApplicationProviderModel.class);
        } catch (IOException e) {
            logger.debug(e.getMessage());
            return null;
        }
        
        return jiraModel;
    }
}
