package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.Issue;
import hubble.backend.storage.models.IssueStorage;
import org.modelmapper.PropertyMap;

public class IssuePropertyMap extends PropertyMap<IssueStorage, Issue> {

    @Override
    protected void configure() {

        map().setState(source.getStatus());

    }
}
