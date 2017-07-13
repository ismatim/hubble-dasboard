
package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.storage.models.AvailabilityStorage;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration {

    private ModelMapper mapper;

    public MapperConfiguration() {
            mapper = new ModelMapper();
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<AvailabilityDto> mapToAvailabilityDtoList(List<AvailabilityStorage> availabilityStorageList){
        Type availabilityDtoTypeList = new TypeToken<List<AvailabilityDto>>() {}.getType();
        return mapper.map(availabilityStorageList,availabilityDtoTypeList);
    }

    public AvailabilityDto mapToAvailabilityDto(AvailabilityStorage availabilityStorage){
        return mapper.map(availabilityStorage, AvailabilityDto.class);
    }
}
