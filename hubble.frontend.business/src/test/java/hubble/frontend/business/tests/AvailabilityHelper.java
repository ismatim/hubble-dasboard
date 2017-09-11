package hubble.frontend.business.tests;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AvailabilityHelper {

    public List<AvailabilityDto> mockData() {
        List<AvailabilityDto> availabilityDtoList = new ArrayList();
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId("1");
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("BancoRipley - HomeBanking");
        availabilityDto.setId("h3y44h5sk58f8sdf48f");
        availabilityDto.setProviderOrigin("AppPulse Active");
        availabilityDto.setScriptName("HomeBanking");
        availabilityDto.setServerName("Arizona");
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis() - Calendar.MINUTE * 9));
        availabilityDto.setTransactionId("h5l394kdd9j393k");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setAvailabilityStatus("OK");

        availabilityDtoList.add(availabilityDto);
        availabilityDto = new AvailabilityDto();

        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("BancoRipley - HomeBanking");
        availabilityDto.setId("h3y44h5sk58f8sdf48f");
        availabilityDto.setProviderOrigin("AppPulse Active");
        availabilityDto.setScriptName("HomeBanking");
        availabilityDto.setServerName("Arizona");
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis() - Calendar.MINUTE * 9));
        availabilityDto.setTransactionId("h5l394kdd9j393k");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setAvailabilityStatus("OK");

        availabilityDtoList.add(availabilityDto);
        availabilityDto = new AvailabilityDto();
        availabilityDto.setApplicationId("3");
        availabilityDto.setApplicationName("BancoRipley - HomeBanking");
        availabilityDto.setId("h3y44h5sk58f8sdf38g");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setProviderOrigin("AppPulse Active");
        availabilityDto.setScriptName("HomeBanking");
        availabilityDto.setServerName("Arizona");
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis() - Calendar.MINUTE * 9));
        availabilityDto.setTransactionId("h5l394kdd9j393k");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setAvailabilityStatus("OK");
        availabilityDtoList.add(availabilityDto);

        return availabilityDtoList;
    }

    public ApplicationAvgDto getAvailabilityApplicationAvg() {
        ApplicationAvgDto aaad = new ApplicationAvgDto();
        aaad.setActive(true);
        aaad.setApplicationId("1");
        aaad.setApplicationName("BancoRipley - HomeBanking");
        aaad.setAvailabilityThreshold(90f);
        aaad.getAvailabilityAverage().set(900f);
        aaad.setCriticalThreshold(12000f);
        aaad.setOkThreshold(8000f);
        aaad.setOutlierThreshold(45000f);
        aaad.getAvailabilityAverage().setStatus(MonitoringFields.STATUS.SUCCESS);
        aaad.setTimeZoneId("");
        return aaad;
    }

    public TransactionAvgDto getTransactionAvailabilityAvgDto() {
        TransactionAvgDto taad = new TransactionAvgDto();

        taad.setApplicationId("1");
        taad.setAverage(900f);
        taad.setCriticalThreshold(12000);
        taad.setOkThreshold(8000);
        taad.setAvailabilityThreshold(90);
        taad.setStatus(MonitoringFields.STATUS.SUCCESS);
        taad.setAssigned(true);
        taad.setScriptName("");
        taad.setTransactionId("");
        taad.setTransactionName("");
        taad.setTransactionType("");

        return taad;
    }

    public ApplicationDto getApplicationDto() {
        ApplicationDto ad = new ApplicationDto();
        ad.setActive(true);
        ad.setApplicationId("1");
        ad.setApplicationName("BancoRipley - HomeBanking");
        ad.setAvailabilityThreshold(90f);
        ad.setCriticalThreshold(12000f);
        ad.setLocations(null);
        ad.setOkThreshold(8000f);
        ad.setOutlierThreshold(45000f);
        ad.setTimeZoneId("");
        ad.setTransactions(null);
        return ad;

    }

    public List<ApplicationDto> getAllMockAplication() {
        List<ApplicationDto> applicationDtoList = new ArrayList();

        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("1");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90f);
        applicationDto.setCriticalThreshold(12000f);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000f);
        applicationDto.setOutlierThreshold(45000f);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);

        applicationDtoList.add(applicationDto);
        applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("2");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90f);
        applicationDto.setCriticalThreshold(12000f);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000f);
        applicationDto.setOutlierThreshold(45000f);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);

        applicationDtoList.add(applicationDto);
        applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("3");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90f);
        applicationDto.setCriticalThreshold(12000f);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000f);
        applicationDto.setOutlierThreshold(45000f);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);

        applicationDtoList.add(applicationDto);

        return applicationDtoList;
    }

    public TransactionDto getMockTransactionDto() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId("h5l3a4kdd9j393k");
        transactionDto.setAvailabilityThreshold(90);
        transactionDto.setCriticalThreshold(12000);
        transactionDto.setOkThreshold(8000);

        return transactionDto;
    }

    public List<TransactionDto> getMockTransactionDtoList() {
        List<TransactionDto> transactionDtoList = new ArrayList();
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId("h5l3a4kdd9j393k");
        transactionDto.setAvailabilityThreshold(90);
        transactionDto.setCriticalThreshold(12000);
        transactionDto.setOkThreshold(8000);
        transactionDtoList.add(transactionDto);

        transactionDto = new TransactionDto();
        transactionDto.setTransactionId("f5l3a4kdd9j393k");
        transactionDto.setAvailabilityThreshold(90);
        transactionDto.setCriticalThreshold(12000);
        transactionDto.setOkThreshold(8000);
        transactionDtoList.add(transactionDto);

        transactionDto = new TransactionDto();
        transactionDto.setTransactionId("f5l3a4kdd9j492k");
        transactionDto.setAvailabilityThreshold(90);
        transactionDto.setCriticalThreshold(12000);
        transactionDto.setOkThreshold(8000);
        transactionDtoList.add(transactionDto);

        return transactionDtoList;
    }
}
