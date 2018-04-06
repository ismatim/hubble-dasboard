package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import java.util.List;

public interface AvailabilityService extends
        OperationsAverageCalculationServiceBase<ApplicationIndicators>,
        OperationsKeyPerformanceIndicatorServiceBase<ApplicationIndicators>,
        ApplicationServiceBase<Availability> {

    public List<Availability> getAll();

    public Availability get(String id);

    public List<Availability> getLast10Minutes();

    public List<Availability> getLastHour();

    public List<Availability> getLastDay();

    public List<Availability> getLastMonth();

}
