package hubble.backend.storage.repositories;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Providers;
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
    public List<AvailabilityStorage> findAvailabilitiesByDurationMinutes(int duration, Providers.PROVIDERS_NAME providerName) {

        Calendar from =  CalendarHelper.getNow();
        from.add(Calendar.MINUTE,  -duration );

        Criteria tenMinutesMinusCriteria = Criteria.where("timeStamp").gte(from.getTime());
        Criteria providerNameCritera = Criteria.where("providerOrigin").is(providerName.toString());

        List<AvailabilityStorage> availabilities = mongo
                .find(Query
                        .query(tenMinutesMinusCriteria.andOperator(providerNameCritera)),
                AvailabilityStorage.class);

        return availabilities;
    }
}
