package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import org.modelmapper.PropertyMap;

public class AvailabilityApplicationAvgPropertyMap extends PropertyMap<AvailabilityApplicationAvgDto, BusinessApplicationAvg>{

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
        map().setStatus(source.getStatus());
        map().setAverage((float)source.getAverage()/100);
    }

}
