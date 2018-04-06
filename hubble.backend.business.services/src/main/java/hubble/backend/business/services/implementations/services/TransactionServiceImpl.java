package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.services.TransactionService;
import hubble.backend.business.services.models.Application;
import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.models.Performance;
import hubble.backend.business.services.models.TransactionAvg;
import hubble.backend.business.services.models.Transaction;
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
import hubble.backend.business.services.interfaces.operations.averages.AvailabilityOperations;
import hubble.backend.business.services.interfaces.operations.averages.PerformanceOperations;

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
    public Transaction findTransactionById(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        return mapper.mapToTransactionDto(transactionStorage);
    }

    @Override
    public List<Transaction> findTransactionsByApplicationId(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        return mapper.mapToApplicationDto(applicationStorage).getTransactions();
    }

    @Override
    public List<Availability> findAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<Availability> findLast10MinutesAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<Availability> findLastHourAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<Availability> findLastMonthAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public List<Availability> findLastDayAvailabilitiesByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        return mapper.mapToAvailabilityDtoList(availabilityStorageList);
    }

    @Override
    public TransactionAvg calculateLast10MinutesAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }
        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastHourAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastDayAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }
        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));
        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastMonthAverageTransactionAvailability(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            transactionAvailabilityAvg.setAverage(availavilityOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(availavilityOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));
        return transactionAvailabilityAvg;
    }

    @Override
    public List<Performance> findPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionId(transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<Performance> findLast10MinutesPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<Performance> findLastHourPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<Performance> findLastDayPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public List<Performance> findLastMonthPerformanceByTransactionId(String transactionId) {
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        return mapper.mapToPerformanceDtoList(availabilityStorageList);
    }

    @Override
    public TransactionAvg calculateLast10MinutesAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastHourAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastDayAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage parentApplicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId);
        Application parentApplicationDto = mapper.mapToApplicationDto(parentApplicationStorage);

        if (!availabilityStorageList.isEmpty()) {
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(parentApplicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }

    @Override
    public TransactionAvg calculateLastMonthAverageTransactionPerformance(String transactionId) {
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        ApplicationStorage applicationStorage = applicationRepository.findApplicationByTransactionId(transactionId);
        TransactionAvg transactionAvailabilityAvg = mapper.mapToTransactionAvailabilityAvg(transactionStorage);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId);
        Application applicationDto = mapper.mapToApplicationDto(applicationStorage);

        if (!availabilityStorageList.isEmpty()) {// data for last month
            transactionAvailabilityAvg.setAverage(performanceOperation.calculateAverage(availabilityStorageList));
        }

        transactionAvailabilityAvg.setStatus(performanceOperation.calculateStatus(applicationDto, transactionAvailabilityAvg.getAverage()));

        return transactionAvailabilityAvg;
    }
}
