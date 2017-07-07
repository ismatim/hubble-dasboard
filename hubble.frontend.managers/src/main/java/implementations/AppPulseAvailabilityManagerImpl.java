/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import com.google.gson.JsonObject;
import hubble.backend.business.domain.AppPulseAvailabilityBusiness;
import hubble.frontend.managers.interfaces.AppPulseAvailabilityManager;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander.jimenez
 */
public class AppPulseAvailabilityManagerImpl implements AppPulseAvailabilityManager {

    @Override
    public JsonObject getSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppPulseAvailabilityBusiness findSampleById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findAllSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findLast10MinutesSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findLastHourSamples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AppPulseAvailabilityBusiness> findLastHourSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
