package hubble.frontend.managers.implementations;

import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.frontend.managers.configurations.mappers.ApplicationMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityApplicationAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityTransactionAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.TransactionMapperConfiguration;
import java.util.List;
import hubble.frontend.managers.interfaces.AvailabilityManager;
import hubble.frontend.managers.models.collections.Availability;
import hubble.frontend.managers.models.aggregations.BusinessApplicationAvg;
import hubble.frontend.managers.models.aggregations.TransactionAvg;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.entities.Transaction;
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
    AvailabilityApplicationAvgMapperConfiguration applicationAvgMapper;
    @Autowired
    AvailabilityTransactionAvgMapperConfiguration transactionAvgMapper;
    @Autowired
    AvailabilityService availabilityService;

    @Override
    public Availability findAvailabilityById(String id) {
        AvailabilityDto availabilityDto = availabilityService.findAvailabilityById(id);
        return availabilityMapper.mapToAvailability(availabilityDto);
    }

    @Override
    public List<Availability> findAllAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findAllAvailabilities();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findAvailabilitiesByApplicationId(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLast10MinutesAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLast10MinutesAvailabilities();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastHourAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastHourAvailabilities();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLast10MinutesAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLast10MinutesAvailabilitiesByApplicationId(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLast10MinutesAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastHourAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastHourAvailabilitiesByApplicationId(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastHourAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public BusinessApplicationAvg findLast10MinutesAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto availabilityAvgDto = availabilityService.calculateLast10MinutesAverageApplicationAvailability(applicationId);
        return applicationAvgMapper.mapToAvailabilityApplicationAvg(availabilityAvgDto);
    }

    @Override
    public BusinessApplicationAvg findLastHourAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto availabilityAvgDto = availabilityService.calculateLastHourAverageApplicationAvailability(applicationId);
        return applicationAvgMapper.mapToAvailabilityApplicationAvg(availabilityAvgDto);
    }

    @Override
    public TransactionAvg findLast10MinutesAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto availabilityAvgDto = availabilityService.calculateLast10MinutesAverageTransactionAvailability(transactionId);
        return transactionAvgMapper.mapToAvailabilityTransactionAvg(availabilityAvgDto);
    }

    @Override
    public TransactionAvg findLastHourAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto availabilityAvgDto = availabilityService.calculateLastHourAverageTransactionAvailability(transactionId);
        return transactionAvgMapper.mapToAvailabilityTransactionAvg(availabilityAvgDto);
    }

    @Override
    public BusinessApplication findBusinessApplicationById(String applicationId) {
        ApplicationDto applicationDto = availabilityService.findApplicationById(applicationId);
        return applicationMapper.mapToBusinessApplication(applicationDto);
    }

    @Override
    public List<BusinessApplication> findAllApplications() {
        List<ApplicationDto> applicationDtoList = availabilityService.findAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }

    @Override
    public Transaction findBusinessTransactionById(String transactionId) {
        TransactionDto transactionDto = availabilityService.findTransactionById(transactionId);
        return transactionMapper.mapToTransaction(transactionDto);
    }

    @Override
    public List<Transaction> findTransactionsByApplication(String applicationId) {
        List<TransactionDto> transactionDtoList = availabilityService.findTransactionsByApplicationId(applicationId);
        return transactionMapper.mapToTransactionList(transactionDtoList);
    }

    @Override
    public List<Availability> findLastDayAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastDayAvailabilities();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastMonthAvailabilities() {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastMonthAvailabilities();
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastDayAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastDayAvailabilitiesByApplicationId(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastDayAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastDayAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastMonthAvailabilitiesByApplicationId(String applicationId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastMonthAvailabilitiesByApplicationId(applicationId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public List<Availability> findLastMonthAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityDto> availabilityDtoList = availabilityService.findLastMonthAvailabilitiesByTransactionId(transactionId);
        return availabilityMapper.mapToAvailabilityList(availabilityDtoList);
    }

    @Override
    public BusinessApplicationAvg findLastDayAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto availabilityAvgDto = availabilityService.calculateLastDayAverageApplicationAvailability(applicationId);
        return applicationAvgMapper.mapToAvailabilityApplicationAvg(availabilityAvgDto);
    }

    @Override
    public BusinessApplicationAvg findLastMonthAverageByApplication(String applicationId) {
        AvailabilityApplicationAvgDto availabilityAvgDto = availabilityService.calculateLastMonthAverageApplicationAvailability(applicationId);
        return applicationAvgMapper.mapToAvailabilityApplicationAvg(availabilityAvgDto);
    }

    @Override
    public TransactionAvg findLastDayAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto availabilityAvgDto = availabilityService.calculateLastDayAverageTransactionAvailability(transactionId);
        return transactionAvgMapper.mapToAvailabilityTransactionAvg(availabilityAvgDto);
    }

    @Override
    public TransactionAvg findLastMonthAverageByTransaction(String transactionId) {
        AvailabilityTransactionAvgDto availabilityAvgDto = availabilityService.calculateLastMonthAverageTransactionAvailability(transactionId);
        return transactionAvgMapper.mapToAvailabilityTransactionAvg(availabilityAvgDto);
    }
}
