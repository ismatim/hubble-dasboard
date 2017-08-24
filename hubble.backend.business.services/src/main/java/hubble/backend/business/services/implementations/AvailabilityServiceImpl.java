package hubble.backend.business.services.implementations;

import hubble.backend.business.services.configurations.mappers.DtoMapperConfiguration;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.HubbleConstants;
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
    DtoMapperConfiguration mapper;
    
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
    public List<AvailabilityDto> findAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
         List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR,transactionId);
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
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.TEN_MINUTES);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_HOUR);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLast10MinutesAverageApplicationAvailability(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last 10 minutes
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastHourAverageApplicationAvailability(String applicationId){
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last hour
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLast10MinutesAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last 10 minutes
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastHourAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last hour
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }
    
    private Integer calculateAverageAvailability(List<AvailabilityStorage> availabilityStorageList){
        int totalAvailabilities = availabilityStorageList.size();
        if(totalAvailabilities==0)
            return null;
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

    @Override
    public List<AvailabilityDto> findLastDayAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_DAY);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);    }

    @Override
    public List<AvailabilityDto> findLastMonthAvailabilities() {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByDurationMonths(HubbleConstants.ONE_MONTH);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastDayAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastDayAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY,transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastMonthAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, applicationId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastMonthAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH,transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastDayAverageApplicationAvailability(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last hour
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastDayAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last hour
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    @Override
    public AvailabilityApplicationAvgDto calculateLastMonthAverageApplicationAvailability(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, applicationId);
        AvailabilityApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);
        
        if(availabilityStorageList.isEmpty()){//No data for last hour
            applicationAvailabilityAvg.setAverage(null);
            applicationAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            applicationAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            applicationAvailabilityAvg.setStatus(calculateAvailabilityStatus(applicationAvailabilityAvg.getAvailabilityThreshold(), applicationAvailabilityAvg.getAverage()));
        }
        return applicationAvailabilityAvg;
    }

    @Override
    public AvailabilityTransactionAvgDto calculateLastMonthAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        AvailabilityTransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);
                
        if(availabilityStorageList.isEmpty()){//No data for last hour
            transactionAvailabilityAvg.setAverage(null);
            transactionAvailabilityAvg.setStatus(MonitoringFields.STATUS.NO_DATA);
        }
        else{
            transactionAvailabilityAvg.setAverage(calculateAverageAvailability(availabilityStorageList));
            transactionAvailabilityAvg.setStatus(calculateAvailabilityStatus(parentApplicationDto.getAvailabilityThreshold(), transactionAvailabilityAvg.getAverage()));
        }
        return transactionAvailabilityAvg;
    }

    
}
