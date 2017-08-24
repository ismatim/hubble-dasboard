package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.aggregations.TransactionAvg;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class ManagerPerformanceMapperConfiguration {

    private ModelMapper mapper;

    public ManagerPerformanceMapperConfiguration() {
            mapper = new ModelMapper();
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public Performance mapToPerformance(PerformanceDto performanceDto){
        if (performanceDto == null) {
            return null;
        }
        return mapper.map(performanceDto, Performance.class);
    }
    
    public List<Performance> mapToPerformanceList(List<PerformanceDto> performanceDtoList){
        if (performanceDtoList == null) {
            return null;
        }
        Type performanceDtoTypeList = new TypeToken<List<Performance>>() {}.getType();
        return mapper.map(performanceDtoList, performanceDtoTypeList);
    }
    
    public BusinessApplicationAvg mapToPerformanceApplicationAvg(AvailabilityApplicationAvgDto performanceAvgDto){
        if (performanceAvgDto == null) {
            return null;
        }
        return mapper.map(performanceAvgDto, BusinessApplicationAvg.class);
    }
    
    public TransactionAvg mapToPerformanceTransactionAvg(AvailabilityTransactionAvgDto performanceAvgDto){
        if (performanceAvgDto == null) {
            return null;
        }
        return mapper.map(performanceAvgDto, TransactionAvg.class);
    }
}
