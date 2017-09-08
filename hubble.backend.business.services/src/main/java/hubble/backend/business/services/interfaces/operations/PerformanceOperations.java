package hubble.backend.business.services.interfaces.operations;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;

public interface PerformanceOperations extends AverageOperationsBase<ApplicationAvgDto> {

    public Float calculateAverage(List<AvailabilityStorage> availabilityStorageList);

    public MonitoringFields.STATUS calculateStatus(ApplicationDto appAvg, Float avgAvailability);
}
