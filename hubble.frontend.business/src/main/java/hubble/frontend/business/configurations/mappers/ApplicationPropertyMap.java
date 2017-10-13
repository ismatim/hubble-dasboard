package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.ApplicationDto;
import hubble.frontend.business.models.BusinessApplication;
import org.modelmapper.PropertyMap;

public class ApplicationPropertyMap extends PropertyMap<ApplicationDto, BusinessApplication> {

    @Override
    protected void configure() {
        map().setId(source.getApplicationId());
        map().setName(source.getApplicationName());
        map().setDisplayName(source.getApplicationName());
        map().setActive(source.isActive());
        map().setTimeZoneId(source.getTimeZoneId());
        map().setTimeZone(source.getTimeZoneId());
        map().setApplicationConfigurationVersion(1);
        map().setFrequencyMin(15); //TODO: Review this statement.
        map().setDefaultPerformanceOkThreshold(source.getOkThreshold());
        map().setDefaultPerformanceCriticalThreshold(source.getCriticalThreshold());
        map().setDefaultPerformanceOutlierThreshold(source.getOutlierThreshold());
        map().setAvailabilityThreshold(source.getAvailabilityThreshold());
        map().setDynamicThresholdEnabled(false);
    }
}
