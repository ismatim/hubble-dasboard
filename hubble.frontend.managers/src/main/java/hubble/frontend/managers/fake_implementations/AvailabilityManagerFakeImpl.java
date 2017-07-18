package hubble.frontend.managers.fake_implementations;

import com.google.gson.JsonObject;
import hubble.backend.business.domain.AvailabilityBusiness;
import hubble.backend.business.domain.ErrorBusiness;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Component;
import hubble.frontend.managers.interfaces.AvailabilityManager;

//@Component
public class AvailabilityManagerFakeImpl implements AvailabilityManager {

    private List<AvailabilityBusiness> fakeSamples;

    @Override
    public AvailabilityBusiness findSampleById(int id) {
        fillFakeData();
        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getSampleId() == id)
                .collect(toList());

        return fakeSamples.get(0);
    }

    @Override
    public List<AvailabilityBusiness> findAllSamples() {
        fillFakeData();
        return fakeSamples;
    }

    @Override
    public List<AvailabilityBusiness> findSamplesByApplicationId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamples() {
        return findSamplesLimitedByTime(null, null
        );
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamples() {
        return findSamplesLimitedByTime(null, null
        );
    }

    @Override
    public List<AvailabilityBusiness> findSamplesLimitedByTime(Date startDate, Date endDate) {
        fillFakeData();
        return fakeSamples;
    }

    @Override
    public List<AvailabilityBusiness> findLast10MinutesSamplesByApplicationId(int id) {
        fillFakeData();
        fakeSamples = findSamplesLimitedByTime(null, null
        );

        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getBusinessApplicationId() == id)
                .collect(toList());

        return fakeSamples;
    }

    @Override
    public List<AvailabilityBusiness> findLastHourSamplesByApplicationId(int id) {
        fillFakeData();
        fakeSamples = findSamplesLimitedByTime(null, null
        );

        fakeSamples = fakeSamples.stream()
                .filter(t -> t.getBusinessApplicationId() == id)
                .collect(toList());

        return fakeSamples;
    }

    private void fillFakeData() {
        fakeSamples = new ArrayList();
        List<ErrorBusiness> fakeErrors = new ArrayList();
        AvailabilityBusiness fakeSample1 = new AvailabilityBusiness();
        fakeSample1.setSampleId(1001);
        fakeSample1.setTimestamp(new Date());
        fakeSample1.setProviderOrigin("AppPulse Active");
        fakeSample1.setProviderName("AppPulse Active Banco Ripley");
        fakeSample1.setBusinessApplicationId(1);
        fakeSample1.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample1.setBusinessProcessId(1);
        fakeSample1.setBusinessProcess("HomeBanking - Portal");
        fakeSample1.setTransactionId(2);
        fakeSample1.setTransaction("Firma Biometrica");
        fakeSample1.setLoctionId(1);
        fakeSample1.setLocationName("Sonda Interna - Ripley 1");
        fakeSample1.setLocationId(1);
        fakeSample1.setAvailabilityStatus("Success");
        fakeSample1.setErrors(fakeErrors);

        AvailabilityBusiness fakeSample2 = new AvailabilityBusiness();
        fakeSample2.setSampleId(1002);
        fakeSample2.setTimestamp(new Date());
        fakeSample2.setProviderOrigin("AppPulse Active");
        fakeSample2.setProviderName("AppPulse Active Banco Ripley");
        fakeSample2.setBusinessApplicationId(1);
        fakeSample2.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample2.setBusinessProcessId(1);
        fakeSample2.setBusinessProcess("HomeBanking - Portal");
        fakeSample2.setTransactionId(2);
        fakeSample2.setTransaction("Carga Mis Productos");
        fakeSample2.setLoctionId(1);
        fakeSample2.setLocationName("Sonda Interna - Ripley 1");
        fakeSample2.setLocationId(1);
        fakeSample2.setAvailabilityStatus("Success");
        fakeSample2.setErrors(fakeErrors);

        AvailabilityBusiness fakeSample3 = new AvailabilityBusiness();
        fakeSample3.setSampleId(1003);
        fakeSample3.setTimestamp(new Date());
        fakeSample3.setProviderOrigin("AppPulse Active");
        fakeSample3.setProviderName("AppPulse Active Banco Ripley");
        fakeSample3.setBusinessApplicationId(1);
        fakeSample3.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample3.setBusinessProcessId(1);
        fakeSample3.setBusinessProcess("HomeBanking - Portal");
        fakeSample3.setTransactionId(2);
        fakeSample3.setTransaction("Firma Biometrica");
        fakeSample3.setLoctionId(1);
        fakeSample3.setLocationName("Sonda Externa, Washington DC");
        fakeSample3.setLocationId(1);
        fakeSample3.setAvailabilityStatus("Success");
        fakeSample3.setErrors(fakeErrors);

        AvailabilityBusiness fakeSample4 = new AvailabilityBusiness();
        fakeSample4.setSampleId(1004);
        fakeSample4.setTimestamp(new Date());
        fakeSample4.setProviderOrigin("AppPulse Active");
        fakeSample4.setProviderName("AppPulse Active Banco Ripley");
        fakeSample4.setBusinessApplicationId(1);
        fakeSample4.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample4.setBusinessProcessId(1);
        fakeSample4.setBusinessProcess("HomeBanking - Portal");
        fakeSample4.setTransactionId(2);
        fakeSample4.setTransaction("Firma Biometrica");
        fakeSample4.setLoctionId(1);
        fakeSample4.setLocationName("Sonda Externa, Washington DC");
        fakeSample4.setLocationId(1);
        fakeSample4.setAvailabilityStatus("Success");
        fakeSample4.setErrors(fakeErrors);

        AvailabilityBusiness fakeSample5 = new AvailabilityBusiness();
        fakeSample5.setSampleId(1005);
        fakeSample5.setTimestamp(new Date());
        fakeSample5.setProviderOrigin("AppPulse Active");
        fakeSample5.setProviderName("AppPulse Active Banco Ripley");
        fakeSample5.setBusinessApplicationId(1);
        fakeSample5.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample5.setBusinessProcessId(1);
        fakeSample5.setBusinessProcess("HomeBanking - Portal");
        fakeSample5.setTransactionId(2);
        fakeSample5.setTransaction("Firma Biometrica");
        fakeSample5.setLoctionId(1);
        fakeSample5.setLocationName("Sonda Interna - Ripley 1");
        fakeSample5.setLocationId(1);
        fakeSample5.setAvailabilityStatus("Success");
        fakeSample5.setErrors(fakeErrors);

        AvailabilityBusiness fakeSample6 = new AvailabilityBusiness();
        fakeSample6.setSampleId(1006);
        fakeSample6.setTimestamp(new Date());
        fakeSample6.setProviderOrigin("AppPulse Active");
        fakeSample6.setProviderName("AppPulse Active Banco Ripley");
        fakeSample6.setBusinessApplicationId(1);
        fakeSample6.setBusinessApplication("BancoRipley - HomeBanking");
        fakeSample6.setBusinessProcessId(1);
        fakeSample6.setBusinessProcess("HomeBanking - Portal");
        fakeSample6.setTransactionId(2);
        fakeSample6.setTransaction("Carga Mis Productos");
        fakeSample6.setLoctionId(1);
        fakeSample6.setLocationName("Sonda Interna - Ripley 1");
        fakeSample6.setLocationId(1);
        fakeSample6.setAvailabilityStatus("Success");
        fakeSample6.setErrors(fakeErrors);

        fakeSamples = new ArrayList();

        fakeSamples.add(fakeSample1);
        fakeSamples.add(fakeSample2);
        fakeSamples.add(fakeSample3);
        fakeSamples.add(fakeSample4);
        fakeSamples.add(fakeSample5);
        fakeSamples.add(fakeSample6);

    }

}
