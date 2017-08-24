package hubble.frontend.managers.tests;

import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.AvailabilityTransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AvailabilityHelper {
    
    public List<AvailabilityDto> mockData(){
        List<AvailabilityDto> availabilityDtoList = new ArrayList();
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId("1");
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("BancoRipley - HomeBanking");
        availabilityDto.setId("h3y44h5sk58f8sdf48f");
        availabilityDto.setProviderOrigin("AppPulse Active");
        availabilityDto.setScriptName("HomeBanking");
        availabilityDto.setServerName("Arizona");
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*9));
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
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*9));
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
        availabilityDto.setTimeStamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*9));
        availabilityDto.setTransactionId("h5l394kdd9j393k");
        availabilityDto.setTransactionName("Despliegue Zona Campana");
        availabilityDto.setAvailabilityStatus("OK");
        availabilityDtoList.add(availabilityDto);

        return availabilityDtoList;
    }
    public AvailabilityApplicationAvgDto getAvailabilityApplicationAvg(){
        AvailabilityApplicationAvgDto aaad = new AvailabilityApplicationAvgDto();
        aaad.setActive(true);
        aaad.setApplicationId("1");
        aaad.setApplicationName("BancoRipley - HomeBanking");
        aaad.setAvailabilityThreshold(90);
        aaad.setAverage(900);
        aaad.setCriticalThreshold(12000);
        aaad.setOkThreshold(8000);
        aaad.setOutlierThreshold(45000);
        aaad.setStatus(MonitoringFields.STATUS.SUCCESS);
        aaad.setTimeZoneId("");
        return aaad;
    }
    
    public AvailabilityTransactionAvgDto getTransactionAvailabilityAvgDto(){
        AvailabilityTransactionAvgDto taad = new AvailabilityTransactionAvgDto();
        
        taad.setApplicationId("1");
        taad.setAverage(900);
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
    
    
    public ApplicationDto getApplicationDto(){
        ApplicationDto ad = new ApplicationDto();
        ad.setActive(true);
        ad.setApplicationId("1");
        ad.setApplicationName("BancoRipley - HomeBanking");
        ad.setAvailabilityThreshold(90);
        ad.setCriticalThreshold(12000);
        ad.setLocations(null);
        ad.setOkThreshold(8000);
        ad.setOutlierThreshold(45000);
        ad.setTimeZoneId("");
        ad.setTransactions(null);
        return ad;
    
    }
    
    public List<ApplicationDto> getAllMockAplication(){
        List<ApplicationDto> applicationDtoList = new ArrayList();
        
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("1");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90);
        applicationDto.setCriticalThreshold(12000);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000);
        applicationDto.setOutlierThreshold(45000);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);
        
        applicationDtoList.add(applicationDto);
        applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("2");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90);
        applicationDto.setCriticalThreshold(12000);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000);
        applicationDto.setOutlierThreshold(45000);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);
        
        applicationDtoList.add(applicationDto);
        applicationDto = new ApplicationDto();
        applicationDto.setActive(true);
        applicationDto.setApplicationId("3");
        applicationDto.setApplicationName("BancoRipley - HomeBanking");
        applicationDto.setAvailabilityThreshold(90);
        applicationDto.setCriticalThreshold(12000);
        applicationDto.setLocations(null);
        applicationDto.setOkThreshold(8000);
        applicationDto.setOutlierThreshold(45000);
        applicationDto.setTimeZoneId("");
        applicationDto.setTransactions(null);
        
        applicationDtoList.add(applicationDto);
        
        return applicationDtoList;
    }
    
    public TransactionDto getMockTransactionDto(){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId("h5l3a4kdd9j393k");
        transactionDto.setAvailabilityThreshold(90);
        transactionDto.setCriticalThreshold(12000);
        transactionDto.setOkThreshold(8000);
        
        return transactionDto;
    }
    
    public List<TransactionDto> getMockTransactionDtoList(){
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
