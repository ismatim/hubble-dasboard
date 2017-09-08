package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.frontend.business.models.BusinessApplicationAvg;
import org.modelmapper.PropertyMap;

public class ApplicationAvgPropertyMap extends PropertyMap<ApplicationAvgDto, BusinessApplicationAvg> {

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
        map().setStatus(source.getAvailabilityAverage().getStatus());
        map().setAverage((float) source.getAvailabilityAverageValue() / 100);
    }

}
