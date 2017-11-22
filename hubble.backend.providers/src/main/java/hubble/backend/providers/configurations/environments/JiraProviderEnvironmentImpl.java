package hubble.backend.providers.configurations.environments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/jira.properties")
public class JiraProviderEnvironmentImpl implements JiraProviderEnvironment{
	
	@Value("${jira.host}")
	private String host;
	@Value("${jira.port}")
	private String port;
	@Value("${jira.user}")
	private String user;
	@Value("${jira.user}")
	private String password;
	
	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return this.host;
	}

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return this.port;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
}
