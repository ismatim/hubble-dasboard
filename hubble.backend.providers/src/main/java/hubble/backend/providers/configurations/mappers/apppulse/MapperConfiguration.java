package hubble.backend.providers.configurations.mappers.apppulse;

import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration {

    private ModelMapper mapper;

    public MapperConfiguration() {
            mapper = new ModelMapper();
            this.mapper.addMappings(new AvailabilityPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<AvailabilityStorage> mapToAvailabilitiesStorage(AvailabilityProviderModel appPulseProv){

          if (appPulseProv == null) {
            return null;
        }

        List<AvailabilityStorage> availabilitiesRecordsToBeSaved = new ArrayList<>();
        appPulseProv.getData().forEach(item -> {
            AvailabilityStorage newAppPulseRecord = new AvailabilityStorage();

            this.mapper.map(item, newAppPulseRecord);
            availabilitiesRecordsToBeSaved.add(newAppPulseRecord);
        });

        return availabilitiesRecordsToBeSaved;
    }

}
