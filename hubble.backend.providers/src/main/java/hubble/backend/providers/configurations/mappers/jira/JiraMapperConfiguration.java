package hubble.backend.providers.configurations.mappers.jira;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JiraMapperConfiguration {

	private ModelMapper mapper;

	public JiraMapperConfiguration() {
		this.mapper = new ModelMapper();
		this.mapper.addMappings(new IssuePropertyMap());
		this.mapper.addMappings(new ApplicationPropertyMap());
	}
}

