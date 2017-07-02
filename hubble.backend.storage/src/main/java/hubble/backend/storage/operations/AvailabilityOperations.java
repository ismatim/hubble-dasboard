package hubble.backend.storage.operations;

import hubble.backend.core.enums.Providers;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;

public interface AvailabilityOperations {
  List<AvailabilityStorage> findAvailabilitiesByDurationMinutes(int duration, Providers.PROVIDERS_NAME providerName );
  boolean exist(AvailabilityStorage availability);
}
