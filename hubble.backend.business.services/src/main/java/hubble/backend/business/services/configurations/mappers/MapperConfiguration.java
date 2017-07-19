package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.ApplicationAvailabilityAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionAvailabilityAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class MapperConfiguration {

    private ModelMapper mapper;

    public MapperConfiguration() {
            mapper = new ModelMapper();
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<AvailabilityDto> mapToAvailabilityDtoList(List<AvailabilityStorage> availabilityStorageList){
        Type availabilityDtoTypeList = new TypeToken<List<AvailabilityDto>>() {}.getType();
        return mapper.map(availabilityStorageList,availabilityDtoTypeList);
    }

    public AvailabilityDto mapToAvailabilityDto(AvailabilityStorage availabilityStorage){
        return mapper.map(availabilityStorage, AvailabilityDto.class);
    }
    
    public ApplicationDto mapToApplicationDto(ApplicationStorage applicationStorage){
        return mapper.map(applicationStorage, ApplicationDto.class);
    }
    
    public ApplicationAvailabilityAvgDto mapToApplicationAvailabilityAvg(ApplicationStorage applicationStorage){
        return mapper.map(applicationStorage, ApplicationAvailabilityAvgDto.class);
    }
    
    public List<ApplicationAvailabilityAvgDto> mapToApplicationAvailabilityAvgList(List<ApplicationStorage> applicationStorageList){
        Type applicationAvailabilityAvgTypeList = new TypeToken<List<ApplicationAvailabilityAvgDto>>() {}.getType();
        return mapper.map(applicationStorageList, applicationAvailabilityAvgTypeList);
    }
    
    public List<ApplicationDto> mapToApplicationDtoList(List<ApplicationStorage> applicationStorageList){
        Type applicationDtoTypeList = new TypeToken<List<ApplicationDto>>() {}.getType();
        return mapper.map(applicationStorageList, applicationDtoTypeList);
    }
    
    public TransactionDto mapToTransactionDto(TransactionStorage transactionStorage){
        return mapper.map(transactionStorage, TransactionDto.class);
    }
    
    public List<TransactionDto> mapToTransactionDtoList(List<TransactionStorage> transactionStorageList){
        Type transactionDtoTypeList = new TypeToken<List<TransactionDto>>() {}.getType();
        return mapper.map(transactionStorageList, transactionDtoTypeList);
    }
    
    public TransactionAvailabilityAvgDto mapToTransactionAvailabilityAvg(TransactionStorage transactionStorage){
        return mapper.map(transactionStorage, TransactionAvailabilityAvgDto.class);
    }
    
    public List<TransactionAvailabilityAvgDto> mapToTransactionAvailabilityAvgList(List<TransactionStorage> transactionStorageList){
        Type transactionAvailabilityAvgTypeList = new TypeToken<List<TransactionAvailabilityAvgDto>>() {}.getType();
        return mapper.map(transactionStorageList, transactionAvailabilityAvgTypeList);
    }
}
