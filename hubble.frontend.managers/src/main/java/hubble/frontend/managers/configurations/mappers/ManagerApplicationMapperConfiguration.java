package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.ApplicationDto;
import hubble.frontend.managers.models.entities.BusinessApplication;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class ManagerApplicationMapperConfiguration {
    
    private ModelMapper mapper;

    public ManagerApplicationMapperConfiguration() {
        mapper = new ModelMapper();
        this.mapper.addMappings(new ApplicationPropertyMap());
    }
    
    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public BusinessApplication mapToBusinessApplication(ApplicationDto applicationDto){
        return mapper.map(applicationDto, BusinessApplication.class);
    }
    
    public List<BusinessApplication> mapToBusinessApplicationList(List<ApplicationDto> applicationDtoList){
        Type applicationDtoTypeList = new TypeToken<List<BusinessApplication>>() {}.getType();
        return mapper.map(applicationDtoList, applicationDtoTypeList);
    }
}
