package hubble.backend.storage.repositories;

import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.operations.AvailabilityOperations;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.repository.MongoRepository;

@Component
public interface AvailabilityRepository  extends MongoRepository<AvailabilityStorage, String>, AvailabilityOperations{
}
