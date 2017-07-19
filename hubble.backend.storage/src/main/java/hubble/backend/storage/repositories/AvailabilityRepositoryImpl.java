package hubble.backend.storage.repositories;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.operations.AvailabilityOperations;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AvailabilityRepositoryImpl implements AvailabilityOperations {

    @Autowired
    MongoOperations mongo;

    @Override
    public List<AvailabilityStorage> findAvailabilitiesByDurationMinutes(int duration) {

        Calendar from = CalendarHelper.getNow();
        from.add(Calendar.MINUTE, -duration);

        Criteria tenMinutesMinusCriteria = Criteria.where("timeStamp").gte(from.getTime());
//        Criteria providerNameCritera = Criteria.where("providerOrigin").is(providerName.toString());

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                        .query(tenMinutesMinusCriteria),
                        AvailabilityStorage.class);

        return availabilities;
    }

    @Override
    public boolean exist(AvailabilityStorage availability) {

        Criteria isSameTimeStamp = Criteria.where("timeStamp").is(availability.getTimeStamp());
        Criteria isSameProvider = Criteria.where("providerOrigin").is(availability.getProviderOrigin());

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                        .query(isSameTimeStamp.andOperator(isSameProvider)),
                        AvailabilityStorage.class);

        return !availabilities.isEmpty();

    }

    @Override
    public List<AvailabilityStorage> findAvailabilitiesByApplicationId(String applicationId) {
        Criteria isApplicationId = Criteria.where("applicationId").is(applicationId);

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                        .query(isApplicationId),
                        AvailabilityStorage.class);

        return availabilities;
    }

    @Override
    public List<AvailabilityStorage> findAvailabilitiesByApplicationIdAndDurationMinutes(int duration, String applicationId) {
        Calendar from = CalendarHelper.getNow();
        from.add(Calendar.MINUTE, -duration);

        Criteria isApplicationId = Criteria.where("applicationId").is(applicationId);
        Criteria durationCriteria = Criteria.where("timeStamp").gte(from.getTime());

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                       .query(durationCriteria.andOperator(isApplicationId)),
                        AvailabilityStorage.class);

        return availabilities;
    }

    @Override
    public List<AvailabilityStorage> findAvailabilitiesByTransactionId(String transactionId) {
        Criteria isTransactionId = Criteria.where("transactionId").is(transactionId);
        
        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                        .query(isTransactionId),
                        AvailabilityStorage.class);

        return availabilities;
    }

    @Override
    public List<AvailabilityStorage> findAvailabilitiesByTransactionIdAndDurationMinutes(int duration, String transactionId) {
        Calendar from = CalendarHelper.getNow();
        from.add(Calendar.MINUTE, -duration);

        Criteria isTransactionId = Criteria.where("transactionId").is(transactionId);
        Criteria durationCriteria = Criteria.where("timeStamp").gte(from.getTime());

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                       .query(durationCriteria.andOperator(isTransactionId)),
                        AvailabilityStorage.class);

        return availabilities;
    }  
}
