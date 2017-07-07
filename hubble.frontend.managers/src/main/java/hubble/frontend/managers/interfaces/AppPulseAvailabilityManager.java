package hubble.frontend.managers.interfaces;

import com.google.gson.JsonObject;
import hubble.backend.business.domain.AppPulseAvailabilityBusiness;
import java.util.Date;
import java.util.List;

public interface AppPulseAvailabilityManager {
    
    public JsonObject getSamples();
    public AppPulseAvailabilityBusiness findSampleById(int id);
    
    public List<AppPulseAvailabilityBusiness> findAllSamples();
    
    public List<AppPulseAvailabilityBusiness> findSamplesByApplicationId(int id);
    
    public List<AppPulseAvailabilityBusiness> findLast10MinutesSamples();
    
    public List<AppPulseAvailabilityBusiness> findLastHourSamples();
    
    public List<AppPulseAvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate);
    
    public List<AppPulseAvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id);
    
    public List<AppPulseAvailabilityBusiness> findLastHourSamplesByApplicationId(int id);

}
