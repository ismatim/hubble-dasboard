package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.TimeStatusOperationsBase;
import hubble.backend.business.services.interfaces.services.UptimeDowntimeService;
import hubble.backend.business.services.models.measures.DowntimeDto;
import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.DateHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UptimeDowntimeServiceImpl implements UptimeDowntimeService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    MapperConfiguration mapper;
    @Autowired
    private TimeStatusOperationsBase timeStatusOperation;
    @Autowired
    private static final Logger logger = Logger.getLogger(UptimeDowntimeServiceImpl.class.getName());

    @Override
    public UptimeDto getUptime(MonitoringFields.FRECUENCY frecuency, Date startDate, Date endDate) {

        if (!validateFrequency(frecuency) || !validateDate(frecuency, startDate, endDate)) {
            return null;
        }

        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, frecuency.getTimeUnit());

        UptimeDto uptime = new UptimeDto();

        TreeMap<Date, Integer> uptimeMap = new TreeMap<>();

        List<Date> days = createPeriodDayListByDay(startDate, endDate);
        for (int x = 0; x < diffCount; x++) {
            List<AvailabilityStorage> availabilities = availabilityRepository.findAvailabilitiesBydAndPeriod(days.get(x), DateHelper.addDaysToDate(days.get(x), 1));
            uptimeMap.put(days.get(x), timeStatusOperation.calculateUptime(availabilities));
        }

        uptime.setUptimes(uptimeMap);
        return uptime;
    }

    @Override
    public DowntimeDto getDowntime(String transactionId, MonitoringFields.FRECUENCY frecuency, Date startDate, Date endDate) {

        if (!validateFrequency(frecuency) || !validateDate(frecuency, startDate, endDate)) {
            return null;
        }

        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, frecuency.getTimeUnit());

        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        DowntimeDto downtime = new DowntimeDto();

        downtime.setTransactionMeasured(mapper.mapToTransactionDto(transactionStorage));

        TreeMap<Date, Integer> downtimeMap = new TreeMap<Date, Integer>();

        List<AvailabilityStorage> availabilities = new ArrayList();
        List<Date> days = createPeriodDayListByDay(startDate, endDate);
        for (int x = 0; x < diffCount; x++) {
            availabilities = availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, days.get(x), DateHelper.addDaysToDate(days.get(x), 1));
            downtimeMap.put(days.get(x), timeStatusOperation.calculateDowntime(availabilities));
        }

        downtime.setDowntimes(downtimeMap);
        return downtime;
    }

    @Override
    public UptimeDto getUptime(String transactionId, MonitoringFields.FRECUENCY frecuency, Date startDate, Date endDate) {

        if (!validateFrequency(frecuency) || !validateDate(frecuency, startDate, endDate)) {
            return null;
        }
        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, frecuency.getTimeUnit());
        TransactionStorage transactionStorage = transactionRepository.findTransactionById(transactionId);
        UptimeDto uptime = new UptimeDto();

        uptime.setTransactionMeasured(mapper.mapToTransactionDto(transactionStorage));
        TreeMap<Date, Integer> uptimeMap = new TreeMap<>();

        List<Date> days = createPeriodDayListByDay(startDate, endDate);
        for (int x = 0; x < diffCount; x++) {
            List<AvailabilityStorage> availabilities = availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, days.get(x), DateHelper.addDaysToDate(days.get(x
            ), 1));
            uptimeMap.put(days.get(x), timeStatusOperation.calculateUptime(availabilities));

        }

        uptime.setUptimes(uptimeMap);
        return uptime;
    }

    @Override
    public UptimeDto getUptimeByApplication(String applicationId, MonitoringFields.FRECUENCY frecuency, Date startDate, Date endDate) {
        if (!validateFrequency(frecuency) || !validateDate(frecuency, startDate, endDate)) {
            return null;
        }
        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, frecuency.getTimeUnit());
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        UptimeDto uptime = new UptimeDto();

        uptime.setApplicationMeasured(mapper.mapToApplicationDto(applicationStorage));
        TreeMap<Date, Integer> uptimeMap = new TreeMap<>();

        List<Date> days = createPeriodDayListByDay(startDate, endDate);

        for (int x = 0; x < diffCount; x++) {

            List<AvailabilityStorage> availabilities = availabilityRepository
                    .findAvailabilitiesByApplicationIdAndPeriod(applicationId, days.get(x), DateHelper.addDaysToDate(days.get(x), 1));

            if (availabilities == null) {
                continue;
            }

            Integer uptimeValueDay = !availabilities.isEmpty() ? timeStatusOperation.calculateUptime(availabilities) : 0;

            uptimeMap.put(days.get(x), uptimeValueDay);
        }

        uptime.setUptimes(uptimeMap);
        return uptime;
    }

    @Override
    public UptimeDto calculateLast10MinutesUptime(String applicationId) {
        UptimeDto uptime = new UptimeDto();
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        uptime.set(timeStatusOperation.calculateUptime(availabilityStorageList).floatValue());
        return uptime;
    }

    @Override
    public UptimeDto calculateLastHourUptime(String applicationId) {

        UptimeDto uptime = new UptimeDto();
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        uptime.set(timeStatusOperation.calculateUptime(availabilityStorageList).floatValue());
        return uptime;
    }

    @Override
    public UptimeDto calculateLastDayUptime(String applicationId) {

        UptimeDto uptime = new UptimeDto();
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        uptime.set(timeStatusOperation.calculateUptime(availabilityStorageList).floatValue());

        return uptime;
    }

    private Boolean validateFrequency(MonitoringFields.FRECUENCY frecuency) {
        if (!frecuency.equals(MonitoringFields.FRECUENCY.DAY)) {
            logger.log(Level.SEVERE, "Not supported yet.");
            return false;
        }
        return true;
    }

    private Boolean validateDate(MonitoringFields.FRECUENCY frecuency, Date startDate, Date endDate) {
        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, frecuency.getTimeUnit());

        if (diffCount <= 0) {
            logger.log(Level.SEVERE, "Date range specified is less than one unit of period");
            return false;
        }
        return true;
    }

    private List<Date> createPeriodDayListByDay(Date startDate, Date endDate) {
        List<Date> days = new ArrayList();
        int diffCount = (int) DateHelper.getDateDiff(startDate, endDate, TimeUnit.DAYS);
        for (int x = 0; x < diffCount; x++) {
            days.add(normalizeToDay(DateHelper.addDaysToDate(startDate, x)));
        }
        return days;
    }

    private Date normalizeToDay(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

}
