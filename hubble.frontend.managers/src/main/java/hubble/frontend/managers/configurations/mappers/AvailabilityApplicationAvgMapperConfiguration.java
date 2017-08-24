package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityApplicationAvgMapperConfiguration {

    private ModelMapper mapper;

    public AvailabilityApplicationAvgMapperConfiguration() {
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AvailabilityApplicationAvgPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BusinessApplicationAvg mapToAvailabilityApplicationAvg(AvailabilityApplicationAvgDto availabilityAvgDto){
        if (availabilityAvgDto == null) {
            return null;
        }
        BusinessApplicationAvg availabilityBusinessApplicationAvg = mapper.map(availabilityAvgDto, BusinessApplicationAvg.class);
        List<String> transactionIds = new ArrayList();
        if(availabilityAvgDto.getTransactions()!=null)
            for(TransactionDto transaction : availabilityAvgDto.getTransactions()){
                transactionIds.add(transaction.getTransactionId());
            }
        availabilityBusinessApplicationAvg.getBusinessApplication().setTransactionIds(transactionIds);
        return availabilityBusinessApplicationAvg;
    }
}
