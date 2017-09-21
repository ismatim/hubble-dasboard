package hubble.backend.storage.repositories;

import hubble.backend.storage.models.WorkItemStorage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import hubble.backend.storage.operations.WorkItemOperations;

@Component
public interface WorkItemRepository extends MongoRepository<WorkItemStorage,String>, WorkItemOperations {   
}
