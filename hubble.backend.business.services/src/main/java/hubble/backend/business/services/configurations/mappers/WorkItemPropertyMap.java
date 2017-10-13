package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.WorkItemDto;
import hubble.backend.storage.models.WorkItemStorage;
import org.modelmapper.PropertyMap;

public class WorkItemPropertyMap extends PropertyMap<WorkItemStorage, WorkItemDto> {

    @Override
    protected void configure() {

        map().setState(source.getStatus());

    }
}
