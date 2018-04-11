package hubble.backend.providers.configurations.mappers.ppm;

import hubble.backend.providers.models.ppm.PpmProgramIssueProviderModel;
import hubble.backend.storage.models.WorkItemStorage;
import org.modelmapper.PropertyMap;

public class WorkItemPropertyMap extends PropertyMap<PpmProgramIssueProviderModel, WorkItemStorage> {

    @Override
    protected void configure() {
        skip().setId(null);
        map().setAssignee(source.getAssignee());
        map().setBusinessApplication(source.getBusinessApplication());
        map().setBusinessApplicationId(source.getApplicationId());
        map().setDescription(source.getDetailedDescription());
        map().setExternalId(source.getId());
        map().setProviderName(source.getProviderName());
        map().setProviderOrigin(source.getProviderOrigin());
        map().setRegisteredBy(source.getCreatedBy());
        map().setStatus(source.getStatusCode());
        map().setTitle(source.getDescription());
        map().setTransaction(source.getTransaction());
        map().setPercentCompleted(source.getPercentComplete());
        map().setTransactionId(source.getTransactionId());
    }

}
