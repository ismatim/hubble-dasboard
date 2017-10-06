package hubble.frontend.business.Implementations;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.interfaces.services.UptimeDowntimeService;
import hubble.backend.business.services.interfaces.services.WorkItemService;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.business.configurations.mappers.ApplicationMapper;
import hubble.frontend.business.configurations.mappers.UptimeMapper;
import hubble.frontend.business.implementations.BusinessApplicationManagerImpl;
import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.models.Uptime;
import hubble.frontend.business.views.application.BusinessApplicationView;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusinessApplicationUnitTest {
    
    @Mock
    AvailabilityService availabilityService;
    @Mock
    PerformanceService performanceService;
    @Mock
    UptimeDowntimeService uptimeService;
    @Mock
    IssueService issueService;
    @Mock
    WorkItemService workItemService;
    @Spy
    UptimeMapper uptimeMapper;
    @Spy
    ApplicationMapper applicationMapper;
    @InjectMocks
    BusinessApplicationManagerImpl businessApplicationManagerImpl;
    String applicationId = "1";
    BusinessApplicationHelper bussinessApplicationHelper = new BusinessApplicationHelper();

    
    @Test
    public void should_return_last_hour_availability(){
        when(availabilityService.getLastHour(applicationId)).thenReturn(bussinessApplicationHelper.mockAvailabilityList());
        List<AvailabilityDto> availabilityDtoList = businessApplicationManagerImpl.getAvailabilityLastHour(applicationId);
        Assert.assertEquals(4, availabilityDtoList.size());
        Assert.assertEquals(applicationId, availabilityDtoList.get(0).getApplicationId() );
    }
    
    @Test
    public void should_return_last_10minutes_availability(){
        when(availabilityService.getLast10Minutes(applicationId)).thenReturn(bussinessApplicationHelper.mockAvailabilityList());
        List<AvailabilityDto> availabilityDtoList = businessApplicationManagerImpl.getAvailabilityLast10Minutes(applicationId);
        Assert.assertEquals(4, availabilityDtoList.size());
        Assert.assertEquals(applicationId, availabilityDtoList.get(0).getApplicationId());
    }
    
    @Test
    public void should_return_last_month_uptime(){
        when(uptimeService.getUptimeByApplication(any(String.class), any(MonitoringFields.FRECUENCY.class), any(Date.class),any(Date.class))).thenReturn(bussinessApplicationHelper.mockUptime());
        List<Uptime> uptimes = businessApplicationManagerImpl.getUptimeLastMonth(applicationId);
        assertEquals(Integer.valueOf(Integer.MIN_VALUE),uptimes.get(0).getUptime());
        assertEquals(Integer.valueOf(Integer.MAX_VALUE),uptimes.get(1).getUptime());
        assertNotNull(uptimes.get(0).getDate());
    }
    
    @Test
    public void should_return_all_applications(){
        when(availabilityService.getAllApplications()).thenReturn(bussinessApplicationHelper.MockApplication());
        List<BusinessApplication> businessApplications = businessApplicationManagerImpl.getAllApplications();
        assertEquals("1", businessApplications.get(0).getId());
        assertEquals(Float.MAX_VALUE, businessApplications.get(0).getAvailabilityThreshold(),00001f);
        assertEquals(Float.MIN_VALUE, businessApplications.get(0).getDefaultPerformanceCriticalThreshold(),00001f);
        assertNotNull(businessApplications);
    }
    
    @Test
    public void should_return_businessApplicationView(){
        when(availabilityService.calculateLast10MinutesAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(performanceService.calculateLast10MinutesAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(availabilityService.calculateLastHourAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(performanceService.calculateLastHourAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(availabilityService.calculateLastDayAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(performanceService.calculateLastDayAverageByApplication(applicationId)).thenReturn(bussinessApplicationHelper.mockApplicationAvg());
        when(issueService.calculateIssuesQuantityLastDay(applicationId)).thenReturn(bussinessApplicationHelper.mockIssuesQuantity());
        when(workItemService.calculateWorkItemQuantityLastDay(applicationId)).thenReturn(bussinessApplicationHelper.mockWorkItemQuantity());
        when(uptimeService.calculateLast10MinutesUptime(applicationId)).thenReturn(bussinessApplicationHelper.mockUptime());
        when(uptimeService.calculateLastHourUptime(applicationId)).thenReturn(bussinessApplicationHelper.mockUptime());
        when(uptimeService.calculateLastDayUptime(applicationId)).thenReturn(bussinessApplicationHelper.mockUptime());
        
        BusinessApplicationView businessApplicationView = businessApplicationManagerImpl.getBusinessApplicationView(applicationId);
        businessApplicationView.setAvailabilityList10Minutes(bussinessApplicationHelper.mockAvailabilityList());
        assertEquals(applicationId, businessApplicationView.getId());
        assertEquals(Float.valueOf(Float.MIN_NORMAL), businessApplicationView.getAvailabilityAverage10min());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(), businessApplicationView.getStatusAvailability10min());
        assertEquals(Integer.valueOf(Integer.SIZE), businessApplicationView.getMeasuresQtyAvailability10min());
        assertEquals(Float.MIN_VALUE, businessApplicationView.getAvailabilityCriticalValue10min(),0.0001f);
        assertEquals(Float.MAX_VALUE, businessApplicationView.getPerformanceAverage1hour(),0.0001f);
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(), businessApplicationView.getStatusPerformance1day());
        assertEquals(Integer.valueOf(Integer.SIZE), businessApplicationView.getIssuesQtyLastDay());
        assertEquals(Integer.valueOf(Integer.SIZE), businessApplicationView.getWorkItems1day());
        assertEquals(Float.MIN_NORMAL, businessApplicationView.getUptime10min(),0.0001f);
        assertEquals(Float.MAX_VALUE,businessApplicationView.getPerformanceAverage10min(),0.001f);
        assertEquals(Integer.valueOf(Integer.SIZE),businessApplicationView.getMeasuresQtyPerformance10min());
        assertEquals(Integer.valueOf(Integer.SIZE),businessApplicationView.getMeasuresQtyAvailability1hour());
        assertEquals(Integer.valueOf(Integer.SIZE),businessApplicationView.getMeasuresQtyPerformance1hour());
        assertEquals(Float.MIN_NORMAL,businessApplicationView.getAvailabilityAverage1day(),0.001f);
        assertEquals(Float.MAX_VALUE,businessApplicationView.getPerformanceAverage1day(),0.001f);
        assertEquals(Integer.SIZE,businessApplicationView.getMeasuresQtyAvailability1day(),0.001f);
        assertEquals(Integer.SIZE,businessApplicationView.getMeasuresQtyPerformance1day(),0.001f);
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusPerformance10min());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusPerformance1hour());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusAvailability1hour());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusAvailability1day());
        assertEquals(Float.MIN_NORMAL,businessApplicationView.getAvailabilityAverage1hour(),0.001f);
        assertEquals(Float.MIN_VALUE,businessApplicationView.getPerformanceCriticalValue10min(),0.001f);
        assertEquals(Float.MIN_VALUE,businessApplicationView.getAvailabilityCriticalValue1hour(),0.001f);
        assertEquals(Float.MIN_VALUE,businessApplicationView.getPerformanceCriticalValue1hour(),0.001f);
        assertEquals(Float.MIN_VALUE,businessApplicationView.getAvailabilityCriticalValue1day(),0.001f);
        assertEquals(Float.MIN_VALUE,businessApplicationView.getPerformanceCriticalValue1day(),0.001f);
        assertEquals(Integer.valueOf(Integer.MIN_VALUE),businessApplicationView.getIssuesQtyCriticalThreshold());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusIssuesQty());
        assertEquals(Float.MIN_NORMAL,businessApplicationView.getUptime1hour(),0.001f);
        assertEquals(Float.MIN_NORMAL,businessApplicationView.getUptime1day(),0.001f);
        assertEquals(Integer.valueOf(Integer.MIN_VALUE),businessApplicationView.getWorkItemsQtyCriticalThreshold());
        assertEquals(MonitoringFields.STATUS.SUCCESS.toString(),businessApplicationView.getStatusWorkItemsQty());
        assertEquals(applicationId,businessApplicationView.getAvailabilityList10Minutes().get(0).getApplicationId());
        
    }
    
}
