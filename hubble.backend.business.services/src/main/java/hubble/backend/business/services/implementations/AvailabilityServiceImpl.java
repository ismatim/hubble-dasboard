package hubble.backend.business.services.implementations;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.models.ApplicationAvailabilityAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.TransactionAvailabilityAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.TransactionRepository;

@Component
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    MapperConfiguration mapper;
    
    @Override
    public TransactionDto findTransactionById(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId); 
        return mapper.mapToTransactionDto(transactionStorage);
    }

    @Override
    public List<TransactionDto> findTransactionsByApplicationId(String applicationId) {
        return findApplicationById(applicationId).getTransactions();
    }

    @Override
    public List<TransactionDto> findAllTransactions() {
        List<TransactionStorage> transactionStorageList = transactionRepository.findAllTransactions();
        return mapper.mapToTransactionDtoList(transactionStorageList);
    }

    @Override
    public List<AvailabilityDto> findAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
         List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(10, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(60,transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }
    
    @Override
    public ApplicationDto findApplicationById(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage);
    }

    @Override
    public List<ApplicationDto> findAllApplications() {
        List<ApplicationStorage> applicationStorageList = applicationRepository.findAllApplications();
        return mapper.mapToApplicationDtoList(applicationStorageList);
    }
    
    @Override
    public List<AvailabilityDto> findAllAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAll();
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityDto findAvailabilityById(String id) {
        return mapper.mapToAvailabilityDto(availabilityRepository.findOne(id));
    }

    @Override
    public List<AvailabilityDto> findAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationId(applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(10);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(60);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public ApplicationAvailabilityAvgDto calculateLast10MinutesAverageApplicationAvailability(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, applicationId);
        ApplicationAvailabilityAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last 10 minutes
            applicationAvailabilityAvg.setAverage(-1);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public ApplicationAvailabilityAvgDto calculateLastHourAverageApplicationAvailability(String applicationId){
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, applicationId);
        ApplicationAvailabilityAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last hour
            applicationAvailabilityAvg.setAverage(-1);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public TransactionAvailabilityAvgDto calculateLast10MinutesAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationById(transactionStorage.getApplicationId());
        TransactionAvailabilityAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(10, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last 10 minutes
            transactionAvailabilityAvg.setAverage(-1);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvailabilityAvgDto calculateLastHourAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationById(transactionStorage.getApplicationId());
        TransactionAvailabilityAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(60, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last hour
            transactionAvailabilityAvg.setAverage(-1);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }
    
    private int calculateAverageAvailability(List<AvailabilityStorage> availabilityStorageList){
        int totalAvailabilities = availabilityStorageList.size();
        if(totalAvailabilities==0)
            return -1;
        int okAvailabilites = 0;
        for(AvailabilityStorage availability : availabilityStorageList){
            if(availability.getAvailabilityStatus().equals(MonitoringFields.STATUS.SUCCESS.toString()))
                okAvailabilites++;    
        }
        return 100*okAvailabilites/totalAvailabilities;
    }

    private MonitoringFields.STATUS calculateAvailabilityStatus(int availabilityThreshold, int avgAvailability){
        if(avgAvailability>=availabilityThreshold)
            return MonitoringFields.STATUS.SUCCESS;
        else if(avgAvailability>=0)
            return MonitoringFields.STATUS.CRITICAL;
        else 
            return MonitoringFields.STATUS.NO_DATA;
    }
}
