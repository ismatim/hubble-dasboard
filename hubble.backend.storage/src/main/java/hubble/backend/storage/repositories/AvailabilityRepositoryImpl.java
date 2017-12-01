package hubble.backend.storage.repositories;

import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.operations.AvailabilityOperations;
import java.util.Calendar;
import java.util.Date;
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

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(tenMinutesMinusCriteria),
						AvailabilityStorage.class);

		return availabilities;
	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByDurationMonths(int duration) {

		Calendar from = CalendarHelper.getNow();
		from.add(Calendar.MONTH, -duration);

		Criteria tenMinutesMinusCriteria = Criteria.where("timeStamp").gte(from.getTime());

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
		Criteria isSameTransactionId = Criteria.where("transactionId").is(availability.getTransactionId());
		Criteria isSameTransactionName = Criteria.where("transactionName").is(availability.getTransactionName());

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(isSameTimeStamp.andOperator(isSameProvider, isSameTransactionId, isSameTransactionName)),
						AvailabilityStorage.class);

		return !availabilities.isEmpty();

	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByApplicationId(String applicationId) {
		Criteria isApplicationId = Criteria.where("applicationName").is(applicationId);

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

		Criteria isApplicationId = Criteria.where("applicationName").is(applicationId);
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

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByApplicationIdAndDurationMonths(int duration, String applicationId) {
		Calendar from = CalendarHelper.getNow();
		from.add(Calendar.MONTH, -duration);

		Criteria isApplicationId = Criteria.where("applicationName").is(applicationId);
		Criteria durationCriteria = Criteria.where("timeStamp").gte(from.getTime());

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(durationCriteria.andOperator(isApplicationId)),
						AvailabilityStorage.class);

		return availabilities;
	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByTransactionIdAndDurationMonths(int duration, String transactionId) {
		Calendar from = CalendarHelper.getNow();
		from.add(Calendar.MONTH, -duration);

		Criteria isTransactionId = Criteria.where("transactionId").is(transactionId);
		Criteria durationCriteria = Criteria.where("timeStamp").gte(from.getTime());

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(durationCriteria.andOperator(isTransactionId)),
						AvailabilityStorage.class);

		return availabilities;
	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByTransactionIdAndPeriod(String transactionId, Date startDate, Date endDate) {
		Criteria isTransactionId = Criteria.where("transactionId").is(transactionId);
		Criteria startDateCriteria = Criteria.where("timeStamp").gte(startDate);
		Criteria endDateCriteria = Criteria.where("timeStamp").lte(endDate);

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(isTransactionId.andOperator(startDateCriteria, endDateCriteria)),
						AvailabilityStorage.class);

		return availabilities;
	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesByApplicationIdAndPeriod(String applicationId, Date startDate, Date endDate) {
		Criteria isAppId = Criteria.where("applicationName").is(applicationId);
		Criteria startDateCriteria = Criteria.where("timeStamp").gte(startDate);
		Criteria endDateCriteria = Criteria.where("timeStamp").lte(endDate);

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(isAppId.andOperator(startDateCriteria, endDateCriteria)),
						AvailabilityStorage.class);

		return availabilities;
	}

	@Override
	public List<AvailabilityStorage> findAvailabilitiesBydAndPeriod(Date startDate, Date endDate) {
		Criteria startDateCriteria = Criteria.where("timeStamp").gte(startDate);
		Criteria endDateCriteria = Criteria.where("timeStamp").lte(endDate);

		List<AvailabilityStorage> availabilities = mongo
				.find(Query
						.query(startDateCriteria.andOperator(endDateCriteria)),
						AvailabilityStorage.class);

		return availabilities;
	}

}
