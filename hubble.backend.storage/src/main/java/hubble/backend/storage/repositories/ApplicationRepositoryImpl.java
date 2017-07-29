package hubble.backend.storage.repositories;

import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.operations.ApplicationOperations;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

//TODO: Reemplazar por el storage real - será necesario usar la API getConfigurations y crear
//las colecciones en Mongo
public class ApplicationRepositoryImpl implements ApplicationOperations {

    @Autowired
    MongoOperations mongo;

    @Override
    public List<ApplicationStorage> findAllApplications() {
        return getApplications();
    }

    @Override
    public ApplicationStorage findApplicationById(String applicationId) {
        List<ApplicationStorage> applicationStorageList = getApplications();
        List<ApplicationStorage> filteredList = applicationStorageList
                .stream()
                .filter(t -> t.getApplicationId() == applicationId)
                .collect(toList());

        return filteredList.get(0);
    }

    @Override
    public boolean exist(ApplicationStorage application) {

        Criteria isSameApplicationId = Criteria.where("applicationId").is(application.getApplicationId());

        List<ApplicationStorage> applications = mongo
                .find(Query
                        .query(isSameApplicationId),
                        ApplicationStorage.class);

        return !applications.isEmpty();

    }

    private List<ApplicationStorage> getApplications() {
        List<ApplicationStorage> applicationStorageList;
        applicationStorageList = mongo.findAll(ApplicationStorage.class);

        return applicationStorageList;
    }

}
