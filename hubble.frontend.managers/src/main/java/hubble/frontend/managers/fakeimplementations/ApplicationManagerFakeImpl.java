
package hubble.frontend.managers.fakeimplementations;

import hubble.frontend.managers.models.BusinessApplication;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;
import hubble.frontend.managers.interfaces.ApplicationManager;

@Component
public class ApplicationManagerFakeImpl implements ApplicationManager{

    private List<BusinessApplication> fakeApplications;
    
    @Override
    public BusinessApplication findBusinessApplicationById(int id) {
        fillFakeData();
        fakeApplications.stream()
                .filter(t -> t.getId() == id)
                .collect(toList());
        
        return fakeApplications.get(0);
    }

    @Override
    public List<BusinessApplication> findAllApplications() {
        fillFakeData();
        return fakeApplications;
    }
    
    private void fillFakeData(){
        fakeApplications = new ArrayList();
        
        BusinessApplication app1 = new BusinessApplication();
        app1.setId(1);
        app1.setBusinessApplicationName("BancoRipley - HomeBanking");
        app1.setBusinessApplicationDisplayName("BancoRipley - HomeBanking");
        
        BusinessApplication app2 = new BusinessApplication();
        app2.setId(2);
        app2.setBusinessApplicationName("BancoRipley - Plataforma Post Venta");
        app2.setBusinessApplicationDisplayName("BancoRipley - Plataforma Post Venta");
        
        BusinessApplication app3 = new BusinessApplication();
        app3.setId(3);
        app3.setBusinessApplicationName("BancoRipley - Sinacofi");
        app3.setBusinessApplicationDisplayName("BancoRipley - Sinacofi");
        
        fakeApplications.add(app1);
        fakeApplications.add(app2);
        fakeApplications.add(app3);
    }
    
}
