package hubble.backend.business.services.configurations.mappers;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.IssueDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.business.services.models.WorkItemDto;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.models.WorkItemStorage;
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
        this.mapper.addMappings(new IssuePropertyMap());
        this.mapper.addMappings(new WorkItemPropertyMap());
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

    public ApplicationAvgDto mapToApplicationAvailabilityAvg(ApplicationStorage applicationStorage) {
        if (applicationStorage == null) {
            return null;
        }

        return mapper.map(applicationStorage, ApplicationAvgDto.class);
    }

    public List<ApplicationAvgDto> mapToApplicationAvailabilityAvgList(List<ApplicationStorage> applicationStorageList) {
        if (applicationStorageList == null) {
            return null;
        }

        Type applicationAvailabilityAvgTypeList = new TypeToken<List<ApplicationAvgDto>>() {
        }.getType();
        return mapper.map(applicationStorageList, applicationAvailabilityAvgTypeList);
    }

    public List<TransactionAvgDto> mapToTransactionAvailabilityAvgList(List<TransactionStorage> transactionStorageList) {
        if (transactionStorageList == null) {
            return null;
        }
        Type transactionAvailabilityAvgTypeList = new TypeToken<List<TransactionAvgDto>>() {
        }.getType();
        return mapper.map(transactionStorageList, transactionAvailabilityAvgTypeList);
    }

    public TransactionAvgDto mapToTransactionAvailabilityAvg(TransactionStorage transactionStorage) {
        if (transactionStorage == null) {
            return null;
        }

        return mapper.map(transactionStorage, TransactionAvgDto.class);
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

    public IssueDto mapToIssueDto(IssueStorage issueStorage) {
        if (issueStorage == null) {
            return null;
        }
        return mapper.map(issueStorage, IssueDto.class);
    }

    public List<IssueDto> mapToIssueDtoList(List<IssueStorage> issueStorageList) {
        if (issueStorageList == null) {
            return null;
        }
        Type issueDtoTypeList = new TypeToken<List<IssueDto>>() {
        }.getType();
        return mapper.map(issueStorageList, issueDtoTypeList);
    }

    public WorkItemDto mapToWorkItemDto(WorkItemStorage workItemStorage) {
        if (workItemStorage == null) {
            return null;
        }
        return mapper.map(workItemStorage, WorkItemDto.class);
    }

    public List<WorkItemDto> mapToWorkItemDtoList(List<WorkItemStorage> workItemStorageList) {
        if (workItemStorageList == null) {
            return null;
        }
        Type issueDtoTypeList = new TypeToken<List<WorkItemDto>>() {
        }.getType();
        return mapper.map(workItemStorageList, issueDtoTypeList);
    }

}
