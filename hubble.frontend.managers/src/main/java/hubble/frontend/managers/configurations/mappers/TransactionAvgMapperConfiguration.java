package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.frontend.managers.models.TransactionAvg;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionAvgMapperConfiguration {

    private ModelMapper mapper;

    public TransactionAvgMapperConfiguration() {
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new TransactionAvgPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TransactionAvg mapToAvailabilityTransactionAvg(TransactionAvgDto availabilityAvgDto) {
        return mapper.map(availabilityAvgDto, TransactionAvg.class);
    }
}
