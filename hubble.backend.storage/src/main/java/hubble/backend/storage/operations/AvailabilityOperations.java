package hubble.backend.storage.operations;

import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;

public interface AvailabilityOperations {
  List<AvailabilityStorage> findAvailabilitiesByDurationMinutes(int duration);
  boolean exist(AvailabilityStorage availability);
  List<AvailabilityStorage> findAvailabilitiesByApplicationId(String applicationId);
  List<AvailabilityStorage> findAvailabilitiesByApplicationIdAndDurationMinutes(int duration, String applicationId);
  List<AvailabilityStorage> findAvailabilitiesByTransactionId(String transactionId);
  List<AvailabilityStorage> findAvailabilitiesByTransactionIdAndDurationMinutes(int duration, String transactionId);
}
