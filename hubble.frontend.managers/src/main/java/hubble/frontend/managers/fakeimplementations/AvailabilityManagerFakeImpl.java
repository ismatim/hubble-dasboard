
package hubble.frontend.managers.fakeimplementations;

import hubble.backend.business.domain.AvailabilityBusiness;
import hubble.backend.business.domain.ErrorBusiness;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;
import hubble.frontend.managers.interfaces.AvailabilityManager;

@Component
public class AvailabilityManagerFakeImpl implements AvailabilityManager{

    private static final int FIVEMINUTES = 300*1000;
    private static final int TENMINUTES = 600*1000;
    private static final int FIFTEENMINUTES = 900*1000;
    private static final int HALFHOUR = 1800*1000;
    private static final int ANHOUR = 3600*1000;
    
    private List<AvailabilityBusiness> fakeSamples;
    
    @Override
    public AvailabilityBusiness findSampleById(int id) {
        fillFakeData();
        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getSampleId()== id)
                .collect(toList());
        
        return fakeSamples.get(0);
    }

    @Override
    public List<AvailabilityBusiness> findAllSamples() {     
        fillFakeData();
        return fakeSamples;
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamples() {
        return findSamplesLimitedByTime(new Date(System.currentTimeMillis()-TENMINUTES), 
                                        new Date(System.currentTimeMillis()+ANHOUR)
                                        );
    }
    
     @Override
    public List<AvailabilityBusiness> findLastHourSamples() {
        return findSamplesLimitedByTime(new Date(System.currentTimeMillis()-ANHOUR), 
                                        new Date(System.currentTimeMillis()+ANHOUR)
                                        );
    }

    @Override
    public List<AvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate) {
        fillFakeData();
        fakeSamples = fakeSamples.stream()
                .filter(sample -> sample.getTimestamp().after(startDate) && sample.getTimestamp().before(endDate))
                .collect(toList());
        
        return fakeSamples;
    }
   
    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id) {
       fillFakeData();
      fakeSamples = findSamplesLimitedByTime(new Date(System.currentTimeMillis()-TENMINUTES), 
                                        new Date(System.currentTimeMillis()+ANHOUR)
                                        );
      
      fakeSamples = fakeSamples.stream()
                .filter(t -> t.getBusinessApplicationId() == id)
                .collect(toList());
       
       return fakeSamples;
    }
    
    @Override
    public List<AvailabilityBusiness> findLastHourSamplesByApplicationId(int id) {
        fillFakeData();
        fakeSamples = findSamplesLimitedByTime(new Date(System.currentTimeMillis()-ANHOUR), 
                                        new Date(System.currentTimeMillis()+ANHOUR)
                                        );
        
        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getBusinessApplicationId() == id)
                .collect(toList());
        
        return fakeSamples;
    }

    @Override
    public List<AvailabilityBusiness> findSamplesByApplicationId(int id) {
        fillFakeData();
        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getBusinessApplicationId() == id)
                .collect(toList());
        
        return fakeSamples;
    }
    
    

     private void fillFakeData() {
        fakeSamples = new ArrayList();
        List<ErrorBusiness> fakeErrors = new ArrayList();
        AvailabilityBusiness fakeSample1 = new AvailabilityBusiness();
        fakeSample1.setAvailabilityStatus("Success");
        fakeSample1.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample1.setBusinessApplicationId(1);
        fakeSample1.setBusinessProcess("HomeBanking - Portal");
        fakeSample1.setBusinessProcessId(1);
        fakeSample1.setErrors(null);
        fakeSample1.setLocationId(1);
        fakeSample1.setLocationName("Sonda Interna - Ripley 1");
        fakeSample1.setLoctionId(1);
        fakeSample1.setProviderName("AppPulse Active Banco Ripley");
        fakeSample1.setProviderOrigin("AppPulse Active");
        fakeSample1.setSampleId(1);
        fakeSample1.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*3));
        fakeSample1.setTransaction("Firma Biometrica");
        fakeSample1.setTransactionId(1);
        
        AvailabilityBusiness fakeSample2 = new AvailabilityBusiness();
        
        fakeSample2.setAvailabilityStatus("Success");
        fakeSample2.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample2.setBusinessApplicationId(1);
        fakeSample2.setBusinessProcess("HomeBanking - Portal");
        fakeSample2.setBusinessProcessId(1);
        fakeSample2.setErrors(null);
        fakeSample2.setLocationId(1);
        fakeSample2.setLocationName("Sonda Interna - Ripley 1");
        fakeSample2.setLoctionId(1);
        fakeSample2.setProviderName("AppPulse Active Banco Ripley");
        fakeSample2.setProviderOrigin("AppPulse Active");
        fakeSample2.setSampleId(2);
        fakeSample2.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*6));
        fakeSample2.setTransaction("Carga Mis Productos");
        fakeSample2.setTransactionId(2);
        
        AvailabilityBusiness fakeSample3 = new AvailabilityBusiness();
        
        fakeSample3.setAvailabilityStatus("Success");
        fakeSample3.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample3.setBusinessApplicationId(1);
        fakeSample3.setBusinessProcess("HomeBanking - Portal");
        fakeSample3.setBusinessProcessId(1);
        fakeSample3.setErrors(null);
        fakeSample3.setLocationId(2);
        fakeSample3.setLocationName("Sonda Externa, Washington DC");
        fakeSample3.setLoctionId(1);
        fakeSample3.setProviderName("AppPulse Active Banco Ripley");
        fakeSample3.setProviderOrigin("AppPulse Active");
        fakeSample3.setSampleId(3);
        fakeSample3.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*9));
        fakeSample3.setTransaction("Firma Biometrica");
        fakeSample3.setTransactionId(1);
        
        AvailabilityBusiness fakeSample4 = new AvailabilityBusiness();
        
        fakeSample4.setAvailabilityStatus("Success");
        fakeSample4.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample4.setBusinessApplicationId(1);
        fakeSample4.setBusinessProcess("HomeBanking - Portal");
        fakeSample4.setBusinessProcessId(1);
        fakeSample4.setErrors(null);
        fakeSample4.setLocationId(2);
        fakeSample4.setLocationName("Sonda Externa, Washington DC");
        fakeSample4.setLoctionId(1);
        fakeSample4.setProviderName("AppPulse Active Banco Ripley");
        fakeSample4.setProviderOrigin("AppPulse Active");
        fakeSample4.setSampleId(4);
        fakeSample4.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*15));
        fakeSample4.setTransaction("Firma Biometrica");
        fakeSample4.setTransactionId(1);
        
        AvailabilityBusiness fakeSample5 = new AvailabilityBusiness();
        
        fakeSample5.setAvailabilityStatus("Success");
        fakeSample5.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample5.setBusinessApplicationId(1);
        fakeSample5.setBusinessProcess("HomeBanking - Portal");
        fakeSample5.setBusinessProcessId(1);
        fakeSample5.setErrors(null);
        fakeSample5.setLocationId(1);
        fakeSample5.setLocationName("Sonda Interna - Ripley 1");
        fakeSample5.setLoctionId(1);
        fakeSample5.setProviderName("AppPulse Active Banco Ripley");
        fakeSample5.setProviderOrigin("AppPulse Active");
        fakeSample5.setSampleId(5);
        fakeSample5.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*20));
        fakeSample5.setTransaction("Firma Biometrica");
        fakeSample5.setTransactionId(1);
        
        AvailabilityBusiness fakeSample6 = new AvailabilityBusiness();
        
        fakeSample6.setAvailabilityStatus("Success");
        fakeSample6.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample6.setBusinessApplicationId(1);
        fakeSample6.setBusinessProcess("HomeBanking - Portal");
        fakeSample6.setBusinessProcessId(1);
        fakeSample6.setErrors(null);
        fakeSample6.setLocationId(1);
        fakeSample6.setLocationName("Sonda Interna - Ripley 1");
        fakeSample6.setLoctionId(1);
        fakeSample6.setProviderName("AppPulse Active Banco Ripley");
        fakeSample6.setProviderOrigin("AppPulse Active");
        fakeSample6.setSampleId(5);
        fakeSample6.setTimestamp(new Date(System.currentTimeMillis()-Calendar.MINUTE*30));
        fakeSample6.setTransaction("Carga Mis Productos");
        fakeSample6.setTransactionId(2);
         
        
        fakeSamples = new ArrayList();
        
        fakeSamples.add(fakeSample1);
        fakeSamples.add(fakeSample2);
        fakeSamples.add(fakeSample3);
        fakeSamples.add(fakeSample4);
        fakeSamples.add(fakeSample5);
        fakeSamples.add(fakeSample6);
        
    }
     
}
