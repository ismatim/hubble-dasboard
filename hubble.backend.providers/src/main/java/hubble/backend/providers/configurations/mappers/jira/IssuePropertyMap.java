package hubble.backend.providers.configurations.mappers.jira;

import org.modelmapper.PropertyMap;
import hubble.backend.providers.models.jira.JiraIssueModel;
import hubble.backend.storage.models.IssueStorage;

public class IssuePropertyMap extends PropertyMap<JiraIssueModel, IssueStorage> {

	@Override
	protected void configure() {

		map().setExternalId(source.getId());
		map().setAssignee(source.getFields().getAssignee().getName());
		map().setDescription(source.getFields().getDescription());
		map().setDetectedBy(source.getFields().getCreator().getName());
		map().setPriority(source.getFields().getPriority().getId());
		map().setStatus(source.getFields().getStatus().getName());
		map().setTitle(source.getFields().getSummary());
		map().setClosedDate(source.getFields().getResolutionDate());
		map().setRegisteredDate(source.getFields().getCreated());
		map().setModifiedDate(source.getFields().getUpdated());
		map().setBusinessApplication(source.getFields().getProject().getName());
	}

}
