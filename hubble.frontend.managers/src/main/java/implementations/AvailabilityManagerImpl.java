/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import com.google.gson.JsonObject;
import hubble.backend.business.domain.AvailabilityBusiness;
import java.util.Date;
import java.util.List;
import hubble.frontend.managers.interfaces.AvailabilityManager;

/**
 *
 * @author alexander.jimenez
 */
public class AvailabilityManagerImpl implements AvailabilityManager {


    @Override
    public AvailabilityBusiness findSampleById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findAllSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
