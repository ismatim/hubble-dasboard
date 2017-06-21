package hubble.backend.providers.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration {

    public ModelMapper mapper;

    MapperConfiguration() {
        mapper = new ModelMapper();
    }
}