package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.business.models.BusinessApplicationAvg;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationAvgMapperConfiguration {

    private ModelMapper mapper;

    public ApplicationAvgMapperConfiguration() {
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new ApplicationAvgPropertyMap());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BusinessApplicationAvg mapToAvailabilityApplicationAvg(ApplicationAvgDto applicationAvgDto) {
        if (applicationAvgDto == null) {
            return null;
        }

        BusinessApplicationAvg availabilityBusinessApplicationAvg = mapper.map(applicationAvgDto, BusinessApplicationAvg.class);
        List<String> transactionIds = new ArrayList();
        if (applicationAvgDto.getTransactions() != null) {
            for (TransactionDto transaction : applicationAvgDto.getTransactions()) {
                transactionIds.add(transaction.getTransactionId());
            }
        }
        availabilityBusinessApplicationAvg.getBusinessApplication().setTransactionIds(transactionIds);
        return availabilityBusinessApplicationAvg;
    }
}
