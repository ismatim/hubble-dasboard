package hubble.frontend.managers.interfaces;

import hubble.backend.business.domain.AvailabilityBusiness;
import java.util.Date;
import java.util.List;


public interface AvailabilityManager {
    
    public AvailabilityBusiness findSampleById(int id);
    
    public List<AvailabilityBusiness> findAllSamples();
    
    public List<AvailabilityBusiness> findSamplesByApplicationId(int id);
    
    public List<AvailabilityBusiness> findLast10MinutesSamples();
    
    public List<AvailabilityBusiness> findLastHourSamples();
    
    public List<AvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate);
    
    public List<AvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id);
    
    public List<AvailabilityBusiness> findLastHourSamplesByApplicationId(int id);

}
