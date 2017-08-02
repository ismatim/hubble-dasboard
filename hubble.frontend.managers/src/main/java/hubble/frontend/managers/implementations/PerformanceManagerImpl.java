package hubble.frontend.managers.implementations;

import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.managers.interfaces.PerformanceManager;
import hubble.frontend.managers.models.aggregations.PerformanceApplicationAvg;
import hubble.frontend.managers.models.aggregations.PerformanceTransactionAvg;
import org.springframework.stereotype.Component;

//TODO: Es necesario reemplazar por las implementaciones que se conectarán a los datos reales
@Component
public class PerformanceManagerImpl implements PerformanceManager{

    @Override
    public PerformanceApplicationAvg findLastHourAverageByApplication(String applicationId) {
        return getFakeApplicationPerformanceData(applicationId);
    }

    @Override
    public PerformanceTransactionAvg findLastHourAverageByTransaction(String transactionId) {
        PerformanceTransactionAvg fakePerf = new PerformanceTransactionAvg();
        fakePerf.setApplicationId("e071193b8376e06554eb2344173cb66d");
        fakePerf.setActive(true);
        fakePerf.setApplicationName("BancoRipley - HomeBanking");
        fakePerf.setAvailabilityThreshold(90);
        fakePerf.setOkThreshold(8000);
        fakePerf.setOutlierThreshold(45000);
        fakePerf.setCriticalThreshold(12000);
        fakePerf.setStatus(MonitoringFields.STATUS.SUCCESS);
        fakePerf.setTimeZoneId("1");
        fakePerf.setAverage(2.40f);

        return fakePerf;
    }

    private PerformanceApplicationAvg getFakeApplicationPerformanceData(String applicationId){
        PerformanceApplicationAvg performanceApplicationAvg = new PerformanceApplicationAvg();

        if(applicationId.equals("e071193b8376e06554eb2344173cb66d")){
            performanceApplicationAvg.setApplicationId("e071193b8376e06554eb2344173cb66d");
            performanceApplicationAvg.setActive(true);
            performanceApplicationAvg.setApplicationName("BancoRipley - HomeBanking");
            performanceApplicationAvg.setAvailabilityThreshold(90);
            performanceApplicationAvg.setOkThreshold(8000);
            performanceApplicationAvg.setOutlierThreshold(45000);
            performanceApplicationAvg.setCriticalThreshold(12000);
            performanceApplicationAvg.setStatus(MonitoringFields.STATUS.SUCCESS);
            performanceApplicationAvg.setTimeZoneId("1");
            performanceApplicationAvg.setAverage(2.40f);
        }
        else{
            performanceApplicationAvg.setApplicationId("b566958ec4ff28028672780d15edcf56");
            performanceApplicationAvg.setActive(true);
            performanceApplicationAvg.setApplicationName("BancoRipley - Sinacofi");
            performanceApplicationAvg.setAvailabilityThreshold(90);
            performanceApplicationAvg.setOkThreshold(8000);
            performanceApplicationAvg.setOutlierThreshold(45000);
            performanceApplicationAvg.setCriticalThreshold(12000);
            performanceApplicationAvg.setStatus(MonitoringFields.STATUS.WARNING);
            performanceApplicationAvg.setTimeZoneId("1");
            performanceApplicationAvg.setAverage(9.70f);
        }

        return performanceApplicationAvg;
    }

}
