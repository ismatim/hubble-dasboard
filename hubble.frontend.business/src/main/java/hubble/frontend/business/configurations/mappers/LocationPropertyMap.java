package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.LocationDto;
import hubble.frontend.business.models.Location;
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
