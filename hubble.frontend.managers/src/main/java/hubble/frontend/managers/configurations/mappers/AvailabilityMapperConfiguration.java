package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.managers.models.collections.Availability;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapperConfiguration {

    private ModelMapper mapper;

    public AvailabilityMapperConfiguration() {
            this.mapper = new ModelMapper();
            this.mapper.addMappings(new AvailabilityPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Availability mapToAvailability(AvailabilityDto availabilityDto){
        if (availabilityDto == null) {
            return null;
        }
        return mapper.map(availabilityDto, Availability.class);
    }

    public List<Availability> mapToAvailabilityList(List<AvailabilityDto> availabilityDtoList){
        if (availabilityDtoList == null) {
            return null;
        }
        Type availabilityDtoTypeList = new TypeToken<List<Availability>>() {}.getType();
        return mapper.map(availabilityDtoList, availabilityDtoTypeList);
    }
}
