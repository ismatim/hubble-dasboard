package hubble.frontend.business.configurations.mappers;

import hubble.backend.business.services.models.PerformanceDto;
import hubble.frontend.business.models.Performance;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class PerformanceMapper {

    private ModelMapper mapper;

    public PerformanceMapper() {
        mapper = new ModelMapper();
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Performance mapToPerformance(PerformanceDto performanceDto) {
        return mapper.map(performanceDto, Performance.class);
    }

    public List<Performance> mapToPerformanceList(List<PerformanceDto> performanceDtoList) {
        Type performanceDtoTypeList = new TypeToken<List<Performance>>() {
        }.getType();
        return mapper.map(performanceDtoList, performanceDtoTypeList);
    }
}
