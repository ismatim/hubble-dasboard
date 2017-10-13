package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.IssueDto;
import hubble.backend.storage.models.IssueStorage;
import org.modelmapper.PropertyMap;

public class IssuePropertyMap extends PropertyMap<IssueStorage, IssueDto> {

    @Override
    protected void configure() {

        map().setState(source.getStatus());

    }
}
