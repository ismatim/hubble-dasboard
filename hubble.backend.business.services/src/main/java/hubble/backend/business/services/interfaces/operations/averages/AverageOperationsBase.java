package hubble.backend.business.services.interfaces.operations.averages;

import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;

public interface AverageOperationsBase<T> {

    public Double calculateAverage(List<AvailabilityStorage> availabilityStorageList);

    public T calculateLast10MinutesAverageByApplication(String applicationId);

    public T calculateLastHourAverageByApplication(String applicationId);

    public T calculateLastDayAverageByApplication(String applicationId);

    public T calculateLastMonthAverageByApplication(String applicationId);
}
