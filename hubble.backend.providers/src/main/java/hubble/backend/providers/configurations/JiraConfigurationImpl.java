package hubble.backend.providers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/jira.config.properties")
public class JiraConfigurationImpl implements JiraConfiguration {
    @Value("${jira.projectKey}")
    private String projectKey;
    @Value("${jira.businessApplication.fieldName}")
    private String applicationFieldName;
    @Value("${jira.businessApplication.valuesToIdMap}")
    private String valuesToIdMap;

    @Override    
    public String getApplicationFieldName() {
        return applicationFieldName;
    }

    @Override
    public String getValuesToIdMap() {
        return valuesToIdMap;
    }
    
    @Override
    public String getProjectKey() {
            return projectKey;
    }
}
