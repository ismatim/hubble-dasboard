package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.Performance;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import java.util.List;

public interface PerformanceService extends
        OperationsAverageCalculationServiceBase<ApplicationIndicators>,
        OperationsKeyPerformanceIndicatorServiceBase<ApplicationIndicators>,
        ApplicationServiceBase<Performance> {

    public List<Performance> getAll();

    public Performance getById(String id);

    public List<Performance> getLast10Minutes();

    public List<Performance> getLastHour();

    public List<Performance> getLastDay();

    public List<Performance> getLastMonth();

}
