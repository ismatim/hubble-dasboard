package hubble.backend.storage.repositories;

import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.operations.ApplicationOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ApplicationRepository extends MongoRepository<ApplicationStorage, String>, ApplicationOperations{   
}
