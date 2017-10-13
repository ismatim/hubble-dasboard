package hubble.backend.providers.configurations.mappers.alm;

import hubble.backend.providers.models.alm.AlmApplicationProviderModel;
import hubble.backend.providers.models.alm.AlmDefectProviderModel;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.IssueStorage;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class AlmMapperConfiguration {
    
    private ModelMapper mapper;
    
    public AlmMapperConfiguration(){
        mapper = new ModelMapper();
        mapper.addMappings(new IssuePropertyMap());
        mapper.addMappings(new ApplicationPropertyMap());
        mapper.addConverter(new AlmDefectToIssueConverter());
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    
    public IssueStorage maptoIssueStorage(AlmDefectProviderModel almProviderModel){
        if(almProviderModel==null)
            return null;
        return mapper.map(almProviderModel, IssueStorage.class);
    }
    
    public List<IssueStorage> maptoIssueStorageList(List<AlmDefectProviderModel> almProviderModel){
        if(almProviderModel==null)
            return null;
        Type typeList = new TypeToken<List<IssueStorage>>() {
        }.getType();
        return mapper.map(almProviderModel, typeList);
    }
    
    public ApplicationStorage mapToApplicationStorage(AlmApplicationProviderModel almApplication){
        if(almApplication==null)
            return null;
        return mapper.map(almApplication, ApplicationStorage.class);
    }
    
    public List<ApplicationStorage> mapToApplicationStorageList(List<AlmApplicationProviderModel> almpplication){
        if(almpplication==null)
            return null;
        Type typeList = new TypeToken<List<ApplicationStorage>>() {
        }.getType();
        return mapper.map(almpplication, typeList);
    }
    
}
