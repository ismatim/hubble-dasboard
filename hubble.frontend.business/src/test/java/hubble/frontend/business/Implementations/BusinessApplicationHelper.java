package hubble.frontend.business.Implementations;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.business.services.models.measures.AvailabilityAverage;
import hubble.backend.business.services.models.measures.AvailabilityQuantity;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.business.services.models.measures.PerformanceAverage;
import hubble.backend.business.services.models.measures.PerformanceQuantity;
import hubble.backend.business.services.models.measures.UptimeDto;
import hubble.backend.business.services.models.measures.WorkItemQuantity;
import hubble.backend.core.enums.MonitoringFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class BusinessApplicationHelper {
    
    Date date = new Date();

    public Date getDate() {
        return date;
    }
    public List<AvailabilityDto> mockAvailabilityList(){
        
        List<AvailabilityDto> availabilityDtos = new ArrayList<>();
        
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("MockName");
        availabilityDto.setAvailabilityStatus("SUCCESS");
        availabilityDto.setTimeStamp(new Date());
        availabilityDtos.add(availabilityDto);
       
        availabilityDto = new AvailabilityDto();
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("MockName2");
        availabilityDto.setAvailabilityStatus("SUCCESS");
        availabilityDto.setTimeStamp(new Date());

        availabilityDtos.add(availabilityDto);
        availabilityDto = new AvailabilityDto();
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("MockName3");
        availabilityDto.setAvailabilityStatus("FAILURE");
        availabilityDto.setTimeStamp(new Date());

        availabilityDtos.add(availabilityDto);
        availabilityDto = new AvailabilityDto();
        availabilityDto.setApplicationId("1");
        availabilityDto.setApplicationName("MockName4");
        availabilityDto.setAvailabilityStatus("SUCCESS");
        availabilityDto.setTimeStamp(new Date());

        availabilityDtos.add(availabilityDto);
        
        return availabilityDtos;
    
    }
    
    public List<ApplicationDto> MockApplication(){
        
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setActive(false);
        applicationDto.setApplicationId("1");
        applicationDto.setApplicationName("MockName");
        applicationDto.setAvailabilityThreshold(Float.MAX_VALUE);
        applicationDto.setCriticalThreshold(Float.MIN_VALUE);
        applicationDto.setLocations(new ArrayList<>());
        applicationDto.setOkThreshold(Float.MIN_NORMAL);
        applicationDto.setOutlierThreshold(Float.MIN_NORMAL);
        applicationDto.setTimeZoneId("1");
        applicationDto.setTransactions(new ArrayList<>());
        applicationDtos.add(applicationDto);
        
        applicationDto = new ApplicationDto();
        applicationDto.setActive(false);
        applicationDto.setApplicationId("2");
        applicationDto.setApplicationName("MockName2");
        applicationDto.setAvailabilityThreshold(Float.MAX_VALUE);
        applicationDto.setCriticalThreshold(Float.MIN_VALUE);
        applicationDto.setLocations(new ArrayList<>());
        applicationDto.setOkThreshold(Float.MIN_NORMAL);
        applicationDto.setOutlierThreshold(Float.MIN_NORMAL);
        applicationDto.setTimeZoneId("2");
        applicationDto.setTransactions(new ArrayList<>());
        applicationDtos.add(applicationDto);
        

        return applicationDtos;
    }
    
    public UptimeDto mockUptime(){
        UptimeDto uptimeDto = new UptimeDto();
        uptimeDto.set(Float.MIN_NORMAL);
        ApplicationDto applicationMeasured = new ApplicationAvgDto();
        applicationMeasured.setCriticalThreshold(Float.MIN_VALUE);
        uptimeDto.setApplicationMeasured(applicationMeasured);
        uptimeDto.setPeriod(MonitoringFields.FRECUENCY.WEEK);
        TransactionDto transactionMeasured = new TransactionAvgDto();
        uptimeDto.setTransactionMeasured(transactionMeasured);
        TreeMap<Date, Integer> uptimes = new TreeMap<>();
        uptimes.put(date, Integer.MIN_VALUE);
        uptimes.put(new Date(), Integer.MAX_VALUE);
        uptimeDto.setUptimes(uptimes);
        return uptimeDto;
    }
    
    public ApplicationAvgDto mockApplicationAvg(){
        ApplicationAvgDto applicationAvgDto= new ApplicationAvgDto();
        applicationAvgDto.setAvailabilityThreshold(Float.MIN_VALUE);
        applicationAvgDto.setCriticalThreshold(Float.MIN_VALUE);
        AvailabilityAverage average = new AvailabilityAverage();
        PerformanceAverage average1 = new PerformanceAverage();
        average.set(Float.MIN_NORMAL);
        average.setStatus(MonitoringFields.STATUS.SUCCESS);
        average1.set(Float.MIN_NORMAL);
        average1.setStatus(MonitoringFields.STATUS.SUCCESS);
        applicationAvgDto.setAvailabilityAverage(average);
        applicationAvgDto.setPerformanceAverage(average1);
        applicationAvgDto.setPerformanceAverage(Float.MAX_VALUE);
        PerformanceQuantity measuresQtyPerformance = new PerformanceQuantity();
        measuresQtyPerformance.setPeriod(MonitoringFields.FRECUENCY.WEEK);
        measuresQtyPerformance.setQuantity(Integer.SIZE);
        measuresQtyPerformance.setStatus(MonitoringFields.STATUS.SUCCESS);
        applicationAvgDto.setMeasuresQtyPerformance(measuresQtyPerformance);                
        AvailabilityQuantity measuresQtyAvailability = new AvailabilityQuantity();
        measuresQtyAvailability.setPeriod(MonitoringFields.FRECUENCY.WEEK);
        measuresQtyAvailability.setQuantity(Integer.SIZE);
        measuresQtyAvailability.setStatus(MonitoringFields.STATUS.SUCCESS);
        applicationAvgDto.setMeasuresQtyAvailability(measuresQtyAvailability);                
        return applicationAvgDto;
    }
    
    public IssuesQuantity mockIssuesQuantity(){
        IssuesQuantity issuesQuantity = new IssuesQuantity();
        issuesQuantity.setQuantity(Integer.SIZE);
        issuesQuantity.setStatus(MonitoringFields.STATUS.SUCCESS);
        issuesQuantity.setCriticalThreshold(Integer.MIN_VALUE);

        return issuesQuantity;
    }
    
    public WorkItemQuantity mockWorkItemQuantity(){
        WorkItemQuantity workItemQuantity = new WorkItemQuantity();
        workItemQuantity.setQuantity(Integer.SIZE);
        workItemQuantity.setStatus(MonitoringFields.STATUS.SUCCESS);
        workItemQuantity.setCriticalThreshold(Integer.MIN_VALUE);
        return workItemQuantity;
    }
}
