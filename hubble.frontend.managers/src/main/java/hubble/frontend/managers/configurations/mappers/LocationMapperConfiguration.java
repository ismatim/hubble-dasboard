package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.LocationDto;
import hubble.frontend.managers.models.entities.Location;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class LocationMapperConfiguration {

    private ModelMapper mapper;

    public LocationMapperConfiguration() {
        mapper = new ModelMapper();
        this.mapper.addMappings(new LocationPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Location mapToLocation(LocationDto locationDto){
        return mapper.map(locationDto, Location.class);
    }

    public List<Location> mapToLocationList(List<LocationDto> locationDtoList){
        Type locationDtoTypeList = new TypeToken<List<Location>>() {}.getType();
        return mapper.map(locationDtoList, locationDtoTypeList);
    }
}
