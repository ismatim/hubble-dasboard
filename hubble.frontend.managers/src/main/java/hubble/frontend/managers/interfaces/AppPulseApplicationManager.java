/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.managers.interfaces;

import hubble.frontend.web.models.AppPulseActiveBusinessApplication;
import java.util.List;

/**
 *
 * @author alexander.jimenez
 */
public interface AppPulseApplicationManager {
    
    public AppPulseActiveBusinessApplication findBusinessApplicationById(int id);
    
    public List<AppPulseActiveBusinessApplication> findAllApplications();
    
}

