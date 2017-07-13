package hubble.frontend.managers.implementations;

import com.google.gson.JsonObject;
import hubble.backend.business.domain.AvailabilityBusiness;
import java.util.Date;
import java.util.List;
import hubble.frontend.managers.interfaces.AvailabilityManager;

public class AvailabilityManagerImpl implements AvailabilityManager {

    @Override
    public AvailabilityBusiness findSampleById(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findAllSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
