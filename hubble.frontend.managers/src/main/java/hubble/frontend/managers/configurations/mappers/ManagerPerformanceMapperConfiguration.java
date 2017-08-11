package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.frontend.managers.models.collections.Performance;
import hubble.frontend.managers.models.aggregations.PerformanceApplicationAvg;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;
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
        return mapper.map(performanceDto, Performance.class);
    }
    
    public List<Performance> mapToPerformanceList(List<PerformanceDto> performanceDtoList){
        Type performanceDtoTypeList = new TypeToken<List<Performance>>() {}.getType();
        return mapper.map(performanceDtoList, performanceDtoTypeList);
    }
    
    public PerformanceApplicationAvg mapToPerformanceApplicationAvg(AvailabilityApplicationAvgDto performanceAvgDto){
        return mapper.map(performanceAvgDto, PerformanceApplicationAvg.class);
    }
    
    public PerformanceTransactionAvg mapToPerformanceTransactionAvg(AvailabilityTransactionAvgDto performanceAvgDto){
        return mapper.map(performanceAvgDto, PerformanceTransactionAvg.class);
    }
}
