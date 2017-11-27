package hubble.backend.providers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/jira.config.properties")
public class JiraConfigurationImpl implements JiraConfiguration {
	@Value("${jira.projectKey}")
	private String projectKey;
	
	@Override
	public String getProjectKey() {
		return projectKey;
	}
}
