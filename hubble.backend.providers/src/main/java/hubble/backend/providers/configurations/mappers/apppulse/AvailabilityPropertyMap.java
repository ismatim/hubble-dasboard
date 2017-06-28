package hubble.backend.providers.configurations.mappers.apppulse;

import hubble.backend.core.utils.Providers;
import hubble.backend.providers.models.apppulse.AvailabilityDataProviderModel;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.Date;
import org.modelmapper.PropertyMap;

public class AvailabilityPropertyMap extends PropertyMap<AvailabilityDataProviderModel, AvailabilityStorage> {

    @Override
    protected void configure() {
        Date timeStamp = new Date(source.getTimeStamp());
        String providerName = Providers.PROVIDERS_NAME.APP_PULSE.toString();

        skip().setId(null);
        map().setTimeStamp(timeStamp);
        map().setProviderOrigin(providerName);
    }
};
