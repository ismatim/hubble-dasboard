package hubble.frontend.managers.implementations;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.interfaces.services.TransactionService;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.configurations.mappers.ApplicationAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.ApplicationMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.TransactionAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.TransactionMapperConfiguration;
import hubble.frontend.managers.interfaces.AvailabilityManager;
import hubble.frontend.managers.models.Availability;
import hubble.frontend.managers.models.BusinessApplication;
import hubble.frontend.managers.models.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityManagerImpl implements AvailabilityManager {

    @Autowired
    AvailabilityMapperConfiguration availabilityMapper;
    @Autowired
    ApplicationMapperConfiguration applicationMapper;
    @Autowired
    TransactionMapperConfiguration transactionMapper;
    @Autowired
    ApplicationAvgMapperConfiguration applicationAvgMapper;
    @Autowired
    TransactionAvgMapperConfiguration transactionAvgMapper;
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    TransactionService transactionService;

    @Override
    public Availability getAvailabilityById(String id) {
        AvailabilityDto availabilityDto = availabilityService.get(id);
        return availabilityMapper.mapToAvailability(availabilityDto);
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getAll();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getAll(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = transactionService.findAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLast10MinutesAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getLast10Minutes();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLastHourAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getLastHour();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLast10MinutesAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getLast10Minutes(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = transactionService.findLast10MinutesAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLastHourAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.getLastHour(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> getLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = transactionService.findLastHourAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public BusinessApplication getBusinessApplicationById(String applicationId) {
        ApplicationDto applicationDto = availabilityService.getApplication(applicationId);
        return applicationMapper.mapToBusinessApplication(applicationDto);
    }

    @Override
    public List<BusinessApplication> getAllApplications() {
        List<ApplicationDto> applicationDtoList = availabilityService.getAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }

    @Override
    public Transaction getBusinessTransactionById(String transactionId) {
        TransactionDto transactionDto = transactionService.findTransactionById(transactionId);
        return transactionMapper.mapToTransaction(transactionDto);
    }

    @Override
    public List<Transaction> getTransactionsByApplication(String applicationId) {
        List<TransactionDto> transactionDtoList = transactionService.findTransactionsByApplicationId(applicationId);
        return transactionMapper.mapToTransactionList(transactionDtoList);
    }
}
