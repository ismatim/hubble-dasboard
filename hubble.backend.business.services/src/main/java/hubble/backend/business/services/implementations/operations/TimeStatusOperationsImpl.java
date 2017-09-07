package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.interfaces.operations.TimeStatusOperationsBase;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TimeStatusOperationsImpl implements TimeStatusOperationsBase {

    @Override
    public Integer calculateUptime(List<AvailabilityStorage> availabilities) {
        int successCounter = 0;
        int totalCounter = availabilities.size();
        if (availabilities.isEmpty()) {
            return null;
        }
        for (AvailabilityStorage availability : availabilities) {
            if (availability.getAvailabilityStatus().equals(MonitoringFields.STATUS.SUCCESS.toString())) {
                successCounter++;
            }
        }
        return 100 * successCounter / totalCounter;
    }

    @Override
    public Integer calculateDowntime(List<AvailabilityStorage> availabilities) {
        int successCounter = 0;
        int totalCounter = availabilities.size();
        if (availabilities.isEmpty()) {
            return null;
        }
        for (AvailabilityStorage availability : availabilities) {
            if (availability.getAvailabilityStatus().equals(MonitoringFields.STATUS.SUCCESS.toString())) {
                successCounter++;
            }
        }
        return 100 - 100 * successCounter / totalCounter;
    }

}
