package hubble.backend.storage.operations;

import hubble.backend.storage.models.ApplicationStorage;
import java.util.List;

public interface ApplicationOperations {
        
    public List<ApplicationStorage> findAllApplications();
    public ApplicationStorage findApplicationById(String applicationId); 
}
