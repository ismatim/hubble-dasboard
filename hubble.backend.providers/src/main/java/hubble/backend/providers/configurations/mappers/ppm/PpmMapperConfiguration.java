package hubble.backend.providers.configurations.mappers.ppm;

import hubble.backend.providers.models.ppm.PpmApplicationProviderModel;
import hubble.backend.providers.models.ppm.PpmProgramIssueProviderModel;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.WorkItemStorage;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class PpmMapperConfiguration {
    
    private ModelMapper mapper;

    public PpmMapperConfiguration() {
        mapper = new ModelMapper();
        mapper.addMappings(new WorkItemPropertyMap());
        mapper.addMappings(new ApplicationPropertyMap());
        mapper.addConverter(new PpmProgramIssueToWorkItemConverter());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public WorkItemStorage mapToWorkItemStorage(PpmProgramIssueProviderModel ppmProviderModel){
        if(ppmProviderModel==null)
            return null;
        return mapper.map(ppmProviderModel, WorkItemStorage.class);
    }
    
    public List<WorkItemStorage> mapToWorkItemStorageList(List<PpmProgramIssueProviderModel> ppmProviderModelList){
        if(ppmProviderModelList==null)
            return null;
        Type typeList = new TypeToken<List<WorkItemStorage>>() {
        }.getType();
        return mapper.map(ppmProviderModelList, typeList);
    }
    
     public ApplicationStorage mapToApplicationStorage(PpmApplicationProviderModel ppmApplication){
        if(ppmApplication==null)
            return null;
        return mapper.map(ppmApplication, ApplicationStorage.class);
    }
     
     public List<ApplicationStorage> mapToApplicationStorageList(List<PpmApplicationProviderModel> ppmApplication){
        if(ppmApplication==null)
            return null;
        Type typeList = new TypeToken<List<ApplicationStorage>>() {
        }.getType();
        return mapper.map(ppmApplication, typeList);
    }
    
}
