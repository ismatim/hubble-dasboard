package hubble.backend.business.services.interfaces.services.averages;

/**
 * Offers the standard operation for a unit of measure.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Model to measure.
 */
public interface OperationsAverageCalculationServiceBase<T> {

    public T calculateLast10MinutesAverageByApplication(String applicationId);

    public T calculateLastHourAverageByApplication(String applicationId);

    public T calculateLastDayAverageByApplication(String applicationId);

    public T calculateLastMonthAverageByApplication(String applicationId);
}
