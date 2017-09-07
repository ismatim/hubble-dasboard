package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.TimeStatusOperationsImpl;
import hubble.backend.business.services.implementations.services.UptimeDowntimeServiceImpl;
import hubble.backend.business.services.models.measures.DowntimeDto;
import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class UptimeDowntimeServiceUnitTests {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AvailabilityRepository availabilityRepository;
    @Spy
    private MapperConfiguration mapper;
    @Spy
    private TimeStatusOperationsImpl timeStatusOperation;
    @InjectMocks
    private UptimeDowntimeServiceImpl service;

    private Date startDate;
    private Date endDate;
    final int ADAYINMILLIS = 86400 * 1000;

    @Test
    public void dependencies_should_be_isntantiated() {
        assertNotNull(transactionRepository);
        assertNotNull(availabilityRepository);
        assertNotNull(mapper);
        assertNotNull(service);
    }

    @Test
    public void verify_availability_and_transaction_repository_mock_usage() {
        //Assign
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        List<AvailabilityStorage> availabilityStorageList = new AvailabilityHelper().mockData();
        Date startDate = new Date(System.currentTimeMillis() - ADAYINMILLIS * 3);
        Date endDate = new Date();

        //Act
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, startDate, endDate)).thenReturn(availabilityStorageList);
        service.getUptime(transactionId, MonitoringFields.FRECUENCY.DAY, startDate, endDate);

        //Assert
        verify(transactionRepository).findTransactionById(transactionId);
        verify(availabilityRepository, Mockito.atLeastOnce()).findAvailabilitiesByTransactionIdAndPeriod(any(), any(), any());
    }

    @Test
    public void service_should_throw_unsupported_exception_for_periods_not_yet_implemented() {
        //Assert
        assertNull(service.getUptime("123", MonitoringFields.FRECUENCY.MINUTE, startDate, endDate));
    }

    @Test
    public void service_should_throw_datetime_exception_for_daterange_lower_than_period_requirement() {
        //Assign
        startDate = new Date(System.currentTimeMillis() - Calendar.HOUR * 50);
        endDate = new Date();
        //Assert
        assertNull(service.getUptime("123", MonitoringFields.FRECUENCY.DAY, startDate, endDate));
    }

    @Test
    public void service_should_return_uptime_by_transaction() {
        //Assign
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        List<AvailabilityStorage> availabilityStorageFirstDay = new AvailabilityHelper().mockThreeDaysAgoData();
        List<AvailabilityStorage> availabilityStorageTwoDaysAgo = new AvailabilityHelper().mockTwoDaysAgoData();
        List<AvailabilityStorage> availabilityStorageADayAgo = new AvailabilityHelper().mockADayAgoData();
        Date startDate = new Date(System.currentTimeMillis() - ADAYINMILLIS * 3);
        Date firstDay = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS * 3));
        Date twoDaysAgo = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS * 2));
        Date oneDayAgo = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS));
        Date lastDay = normalizeToDay(new Date());
        Date endDate = new Date();

        //Act
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, firstDay, twoDaysAgo)).thenReturn(availabilityStorageFirstDay);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, twoDaysAgo, oneDayAgo)).thenReturn(availabilityStorageTwoDaysAgo);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, oneDayAgo, lastDay)).thenReturn(availabilityStorageADayAgo);
        UptimeDto uptime = service.getUptime(transactionId, MonitoringFields.FRECUENCY.DAY, startDate, endDate);

        //Assert
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, firstDay, twoDaysAgo);
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, twoDaysAgo, oneDayAgo);
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, oneDayAgo, lastDay);

        assertEquals(uptime.getTransactionMeasured().getTransactionName(), "Auntenticacion Biometrica");

        for (Map.Entry<Date, Integer> entry : uptime.getUptimes().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        assertEquals(58, uptime.getUptimes().get(firstDay).intValue());
        assertEquals(45, uptime.getUptimes().get(twoDaysAgo).intValue());
        assertEquals(100, uptime.getUptimes().get(oneDayAgo).intValue());

    }

    @Test
    public void service_should_return_downtime_by_transaction() {
        //Assign
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        List<AvailabilityStorage> availabilityStorageFirstDay = new AvailabilityHelper().mockThreeDaysAgoData();
        List<AvailabilityStorage> availabilityStorageTwoDaysAgo = new AvailabilityHelper().mockTwoDaysAgoData();
        List<AvailabilityStorage> availabilityStorageADayAgo = new AvailabilityHelper().mockADayAgoData();
        Date startDate = new Date(System.currentTimeMillis() - ADAYINMILLIS * 3);
        Date firstDay = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS * 3));
        Date twoDaysAgo = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS * 2));
        Date oneDayAgo = normalizeToDay(new Date(System.currentTimeMillis() - ADAYINMILLIS));
        Date lastDay = normalizeToDay(new Date());
        Date endDate = new Date();

        //Act
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, firstDay, twoDaysAgo)).thenReturn(availabilityStorageFirstDay);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, twoDaysAgo, oneDayAgo)).thenReturn(availabilityStorageTwoDaysAgo);
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndPeriod(transactionId, oneDayAgo, lastDay)).thenReturn(availabilityStorageADayAgo);
        DowntimeDto downtime = service.getDowntime(transactionId, MonitoringFields.FRECUENCY.DAY, startDate, endDate);

        //Assert
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, firstDay, twoDaysAgo);
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, twoDaysAgo, oneDayAgo);
        verify(availabilityRepository).findAvailabilitiesByTransactionIdAndPeriod(transactionId, oneDayAgo, lastDay);

        assertEquals(downtime.getTransactionMeasured().getTransactionName(), "Auntenticacion Biometrica");

        for (Map.Entry<Date, Integer> entry : downtime.getDowntimes().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        assertEquals(42, downtime.getDowntimes().get(firstDay).intValue());
        assertEquals(55, downtime.getDowntimes().get(twoDaysAgo).intValue());
        assertEquals(0, downtime.getDowntimes().get(oneDayAgo).intValue());

    }

    private Date normalizeToDay(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }
}
