package hubble.backend.storage.repositories;

import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.operations.ApplicationOperations;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

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
                .filter(t -> t.getApplicationId()==applicationId)
                .collect(toList());
        
        return filteredList.get(0);
    }

    private List<ApplicationStorage> getApplications(){
        List<ApplicationStorage> applicationStorageList = new ArrayList();
        ApplicationStorage application1 = new ApplicationStorage();
        ApplicationStorage application2 = new ApplicationStorage();
        ApplicationStorage application3 = new ApplicationStorage();
        
        application1.setApplicationId("b566958ec4ff28028672780d15edcf56");
        application1.setApplicationName("BancoRipley - Sinacofi");
        application1.setActive(true);        
        application1.setOkThreshold(8000);
        application1.setCriticalThreshold(12000);
        application1.setOutlierThreshold(45000);
        application1.setAvailabilityThreshold(90);
        application1.setLocations(null);
        application1.setTransactions(null);
        application1.setTimeZoneId("1");
        
        application2.setApplicationId("b566958ec4ff28028672780d15edcf56");
        application2.setApplicationName("BancoRipley - Sinacofi");
        application2.setActive(true);        
        application2.setOkThreshold(8000);
        application2.setCriticalThreshold(12000);
        application2.setOutlierThreshold(45000);
        application2.setAvailabilityThreshold(90);
        application2.setLocations(null);
        application2.setTransactions(null);
        application2.setTimeZoneId("1");
        
        application3.setApplicationId("e071193b8376e06554eb2344173cb66d");
        application3.setApplicationName("BancoRipley - HomeBanking");
        application3.setActive(true);        
        application3.setOkThreshold(8000);
        application3.setCriticalThreshold(12000);
        application3.setOutlierThreshold(45000);
        application3.setAvailabilityThreshold(90);
        application3.setLocations(null);
        application3.setTransactions(null);
        application3.setTimeZoneId("1");
        
        applicationStorageList.add(application1);
        applicationStorageList.add(application2);
        applicationStorageList.add(application3);
        
        return applicationStorageList;
    }
    
}
