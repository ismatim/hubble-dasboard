/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.managers.fake_implementations;

import hubble.frontend.managers.interfaces.AppPulseApplicationManager;
import hubble.frontend.web.models.AppPulseActiveBusinessApplication;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;

/**
 *
 * @author alexander.jimenez
 */
@Component
public class AppPulseApplicationManagerFakeImpl implements AppPulseApplicationManager{

    private List<AppPulseActiveBusinessApplication> fakeApplications;

    @Override
    public AppPulseActiveBusinessApplication findBusinessApplicationById(int id) {
        fillFakeData();
        fakeApplications.stream()
                .filter(t -> t.getId() == id)
                .collect(toList());
        
        return fakeApplications.get(0);
    }

    @Override
    public List<AppPulseActiveBusinessApplication> findAllApplications() {
        fillFakeData();
        return fakeApplications;
    }
    
    private void fillFakeData(){
        fakeApplications = new ArrayList();
        
        AppPulseActiveBusinessApplication app1 = new AppPulseActiveBusinessApplication();
        app1.setId(1);
        app1.setBusinessApplicationName("BancoRipley - HomeBanking");
        app1.setBusinessApplicationDisplayName("BancoRipley - HomeBanking");
        
        AppPulseActiveBusinessApplication app2 = new AppPulseActiveBusinessApplication();
        app2.setId(2);
        app2.setBusinessApplicationName("BancoRipley - Plataforma Post Venta");
        app2.setBusinessApplicationDisplayName("BancoRipley - Plataforma Post Venta");
        
        AppPulseActiveBusinessApplication app3 = new AppPulseActiveBusinessApplication();
        app3.setId(3);
        app3.setBusinessApplicationName("BancoRipley - Sinacofi");
        app3.setBusinessApplicationDisplayName("BancoRipley - Sinacofi");
        
        fakeApplications.add(app1);
        fakeApplications.add(app2);
        fakeApplications.add(app3);
    }

    
}
