package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.frontend.managers.models.aggregations.AvailabilityTransactionAvg;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityTransactionAvgMapperConfiguration {

    private ModelMapper mapper;

    public AvailabilityTransactionAvgMapperConfiguration() {
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AvailabilityTransactionAvgPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AvailabilityTransactionAvg mapToAvailabilityTransactionAvg(AvailabilityTransactionAvgDto availabilityAvgDto){
        return mapper.map(availabilityAvgDto, AvailabilityTransactionAvg.class);
    }
}
