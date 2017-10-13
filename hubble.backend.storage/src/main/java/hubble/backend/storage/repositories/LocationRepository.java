package hubble.backend.storage.repositories;

import hubble.backend.storage.models.LocationStorage;
import hubble.backend.storage.operations.LocationOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface LocationRepository extends MongoRepository<LocationStorage, String>, LocationOperations{    
}
