package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.managers.models.collections.Availability;
import org.modelmapper.PropertyMap;

public class AvailabilityPropertyMap extends PropertyMap<AvailabilityDto, Availability>{

    //TODO: En este mapping hay algunos valores que no vienen de origen, hay que cambiar el backend
    @Override
    protected void configure() {

        map().getBusinessApplication().setId(source.getApplicationId());
        map().getBusinessApplication().setName(source.getApplicationName());
        map().getBusinessApplication().setDisplayName(source.getApplicationName());
        map().getBusinessApplication().setActive(true);
        map().getBusinessApplication().setTimeZoneId("1");
        map().getBusinessApplication().setTimeZone("1");
        map().getBusinessApplication().setApplicationConfigurationVersion(1);
        map().getBusinessApplication().setFrequencyMin(15);
        map().getBusinessApplication().setAvailabilityThreshold(source.getAvailabilityThreshold());
        map().getTransaction().setId(source.getTransactionId());
        map().getTransaction().setName(source.getTransactionName());
        map().getTransaction().setDisplayName(source.getTransactionName());
        map().getTransaction().setType("script");
        map().getTransaction().setScriptName(source.getScriptName());
        map().getTransaction().setAssignedToLocation(true);
        map().getLocation().setId(source.getLocationId());
        map().getLocation().setName(source.getLocationName());
        map().getLocation().setDisplayName(source.getLocationName());
        map().getLocation().setType("Private");
        map().setErrors(null);
        map().setId(source.getId());
        map().setNumberOfErrors(source.getNumberOfErrors());
        map().setProviderOrigin(source.getProviderOrigin());
        map().setStatus(source.getAvailabilityStatus());
        map().setTimeStamp(source.getTimeStamp());
    }

}
