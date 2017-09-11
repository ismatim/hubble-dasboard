package hubble.backend.providers.configurations.mappers.bsm;

import hubble.backend.core.enums.Providers;
import hubble.backend.providers.models.bsm.BsmProviderModel;
import hubble.backend.storage.models.ApplicationStorage;
import org.modelmapper.PropertyMap;

public class ApplicationPropertyMap extends PropertyMap<BsmProviderModel, ApplicationStorage> {

    @Override
    protected void configure() {

        skip().setId(null);
        skip().setOutlierThreshold(0);

        map().setApplicationId(source.getProfile_name());
        map().setCriticalThreshold(source.getDredthreshold());
        map().setOkThreshold(source.getDgreenthreshold());
        map().setApplicationName(source.getProfile_name());
        map().setActive(true);
    }

};
