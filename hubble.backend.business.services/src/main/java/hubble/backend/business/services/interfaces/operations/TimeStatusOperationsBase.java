package hubble.backend.business.services.interfaces.operations;

import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;

public interface TimeStatusOperationsBase {

    public Integer calculateDowntime(List<AvailabilityStorage> availabilities);

    public Integer calculateUptime(List<AvailabilityStorage> availabilities);
}
