package hubble.backend.providers.configurations.mappers.jira;

import hubble.backend.providers.configurations.JiraConfiguration;
import hubble.backend.providers.models.jira.JiraApplicationProviderModel;
import hubble.backend.providers.models.jira.JiraIssueModel;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.IssueStorage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JiraMapperConfiguration {
    
    @Autowired
    private JiraConfiguration jiraConfig;
    private ModelMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(JiraMapperConfiguration.class);

    public JiraMapperConfiguration() {
            this.mapper = new ModelMapper();
            this.mapper.addMappings(new IssuePropertyMap());
            this.mapper.addMappings(new ApplicationPropertyMap());
    }

    public ModelMapper getMapper() {
        return this.mapper;
    }

    public IssueStorage mapToIssueStorage(JiraIssueModel jiraModel) {
        if (jiraModel == null)
            return null;
        return mapper.map(jiraModel, IssueStorage.class);
    }

    public ApplicationStorage mapToApplicationStorage(JiraApplicationProviderModel jiraModel) {
        if (jiraModel == null)
            return null;
        return mapper.map(jiraModel, ApplicationStorage.class);
    }

    public JiraApplicationProviderModel mapToApplicationModel(JSONObject data) {
        JiraApplicationProviderModel jiraModel = new JiraApplicationProviderModel();
        jiraModel.setName(getValue(data, jiraConfig.getApplicationFieldName()));
        jiraModel.setApplicationId(resolveApplicationId(jiraModel.getName()));
        
        return jiraModel;
    }

    public String getValue(JSONObject data, String fieldName) {
        JSONArray issues = data.getJSONArray("issues");
        JSONObject issue = issues.getJSONObject(0);            
        JSONObject project = issue.getJSONObject(fieldName);

        if (project.has("name")) {           
            return project.get("name").toString();
        } else return null;
    }

    public String resolveApplicationId(String applicationName) {
        String[] applicationsIdMap = jiraConfig.getValuesToIdMap().split(",");
        for (String applicationId : applicationsIdMap) {
            if (applicationName.equals(applicationId.split(":")[0])) {
                return applicationId.split(":")[1];
            }
        }
        logger.debug("Jira: could not found application id in values passed over configuration file");
        return null;
    } 
}

