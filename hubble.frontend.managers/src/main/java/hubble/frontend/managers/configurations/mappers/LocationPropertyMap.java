package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.LocationDto;
import hubble.backend.business.services.models.LocationDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.models.entities.Location;
import org.modelmapper.PropertyMap;

public class LocationPropertyMap extends PropertyMap<LocationDto, Location> {

    @Override
    protected void configure() {
        map().setId(source.getLocationId());
        map().setName(source.getLocationName());
        map().setDisplayName(source.getLocationName());
        map().setType("script");
    }
}
