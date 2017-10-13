package hubble.backend.providers.configurations.mappers.apppulse;

import hubble.backend.providers.models.apppulse.ApplicationData;
import hubble.backend.storage.models.ApplicationStorage;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

public class ApplicationPropertyMap extends PropertyMap<ApplicationData, ApplicationStorage> {

    @Override
    protected void configure() {


        skip().setId(null);
        skip().setOutlierThreshold(0);
        map().setCriticalThreshold(source.getDefaultPerformanceCriticalThreshold());
        map().setOkThreshold(source.getDefaultPerformanceOkThreshold());
        map().setActive(source.getApplicationIsActive());

        using(statusCriticalIfLessThanConverter).map(source.getStatusCriticalIfLessThan()).setAvailabilityThreshold(0);

    }

    Converter<String, Integer> statusCriticalIfLessThanConverter = (MappingContext<String, Integer> context) -> {
        return (int) Double.parseDouble(context.getSource().replaceAll("%", ""));
    };
};
