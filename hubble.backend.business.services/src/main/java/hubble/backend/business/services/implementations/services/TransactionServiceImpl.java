package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.services.TransactionService;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hubble.backend.business.services.interfaces.operations.AvailabilityOperations;
import hubble.backend.business.services.interfaces.operations.PerformanceOperations;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AvailabilityOperations availavilityOperation;
    @Autowired
    PerformanceOperations performanceOperation;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public TransactionDto findTransactionById(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        return mapper.mapToTransactionDto(transactionStorage);
    }

    @Override
    public List<TransactionDto> findTransactionsByApplicationId(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage).getTransactions();
    }

    @Override
    public List<AvailabilityDto> findAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastMonthAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<AvailabilityDto> findLastDayAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public TransactionAvgDto calculateLast10MinutesAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }
        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastHourAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastDayAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }
        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));
        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastMonthAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));
        return transactionAvailabilityAvg;
    }

    @Override
    public List<PerformanceDto> findPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLast10MinutesPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastHourPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastDayPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<PerformanceDto> findLastMonthPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public TransactionAvgDto calculateLast10MinutesAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastHourAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastDayAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        ApplicationDto parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvgDto calculateLastMonthAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage applicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvgDto transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        ApplicationDto applicationDto = mapper.mapToApplicationDto(applicationStorage);

        if (!availabilityStorageList.isEmpty()) {// data for last month
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(applicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }
}
