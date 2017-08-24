package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
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
public class DtoMapperConfiguration {

    private ModelMapper mapper;

    public DtoMapperConfiguration() {
        mapper = new ModelMapper();
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<AvailabilityDto> mapToAvailabilityDtoList(List<AvailabilityStorage> availabilityStorageList) {
        if (availabilityStorageList == null) {
            return null;
        }
        Type availabilityDtoTypeList = new TypeToken<List<AvailabilityDto>>() {
        }.getType();
        return mapper.map(availabilityStorageList, availabilityDtoTypeList);
    }

    public AvailabilityDto mapToAvailabilityDto(AvailabilityStorage availabilityStorage) {
        if (availabilityStorage == null) {
            return null;
        }
        return mapper.map(availabilityStorage, AvailabilityDto.class);
    }

    public ApplicationDto mapToApplicationDto(ApplicationStorage applicationStorage) {
        if (applicationStorage == null) {
            return null;
        }
        return mapper.map(applicationStorage, ApplicationDto.class);
    }

    public List<ApplicationDto> mapToApplicationDtoList(List<ApplicationStorage> applicationStorageList) {
        if (applicationStorageList == null) {
            return null;
        }
        Type applicationDtoTypeList = new TypeToken<List<ApplicationDto>>() {
        }.getType();
        return mapper.map(applicationStorageList, applicationDtoTypeList);
    }

    public TransactionDto mapToTransactionDto(TransactionStorage transactionStorage) {
        if (transactionStorage == null) {
            return null;
        }
        return mapper.map(transactionStorage, TransactionDto.class);
    }

    public List<TransactionDto> mapToTransactionDtoList(List<TransactionStorage> transactionStorageList) {
        if (transactionStorageList == null) {
            return null;
        }
        Type transactionDtoTypeList = new TypeToken<List<TransactionDto>>() {
        }.getType();
        return mapper.map(transactionStorageList, transactionDtoTypeList);
    }

    public AvailabilityApplicationAvgDto mapToApplicationAvailabilityAvg(ApplicationStorage applicationStorage) {
        if (applicationStorage == null) {
            return null;
        }
        return mapper.map(applicationStorage, AvailabilityApplicationAvgDto.class);
    }

    public List<AvailabilityApplicationAvgDto> mapToApplicationAvailabilityAvgList(List<ApplicationStorage> applicationStorageList) {
        if (applicationStorageList == null) {
            return null;
        }
        Type applicationAvailabilityAvgTypeList = new TypeToken<List<AvailabilityApplicationAvgDto>>() {
        }.getType();
        return mapper.map(applicationStorageList, applicationAvailabilityAvgTypeList);
    }

    public List<AvailabilityTransactionAvgDto> mapToTransactionAvailabilityAvgList(List<TransactionStorage> transactionStorageList) {
        if (transactionStorageList == null) {
            return null;
        }
        Type transactionAvailabilityAvgTypeList = new TypeToken<List<AvailabilityTransactionAvgDto>>() {
        }.getType();
        return mapper.map(transactionStorageList, transactionAvailabilityAvgTypeList);
    }

    public AvailabilityTransactionAvgDto mapToTransactionAvailabilityAvg(TransactionStorage transactionStorage) {
        if (transactionStorage == null) {
            return null;
        }
        return mapper.map(transactionStorage, AvailabilityTransactionAvgDto.class);
    }

    public List<PerformanceDto> mapToPerformanceDtoList(List<AvailabilityStorage> availabilityStorageList) {
        if (availabilityStorageList == null) {
            return null;
        }
        Type availabilityDtoTypeList = new TypeToken<List<AvailabilityDto>>() {
        }.getType();
        return mapper.map(availabilityStorageList, availabilityDtoTypeList);
    }

    public PerformanceDto mapToPerformanceDto(AvailabilityStorage availabilityStorage) {
        if (availabilityStorage == null) {
            return null;
        }
        return mapper.map(availabilityStorage, PerformanceDto.class);
    }

}
