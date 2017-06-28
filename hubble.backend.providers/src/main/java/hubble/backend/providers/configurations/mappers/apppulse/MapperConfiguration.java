package hubble.backend.providers.configurations.mappers.apppulse;

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

}
