package hubble.backend.storage.repositories;

import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.operations.TransactionOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface TransactionRepository extends MongoRepository<TransactionStorage, String>, TransactionOperations{    
}
