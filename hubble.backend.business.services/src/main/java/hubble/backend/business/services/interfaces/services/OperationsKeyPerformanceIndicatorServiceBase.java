package hubble.backend.business.services.interfaces.services;

/**
 * Offers the standard operation for a unit of measure.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Model to measure.
 */
public interface OperationsKeyPerformanceIndicatorServiceBase<T> {

    public T calculateLast10MinutesKpiByApplication(String applicationId);

    public T calculateLastHourKpiByApplication(String applicationId);

    public T calculateLastDayKpiByApplication(String applicationId);

    public T calculateLastMonthKpiByApplication(String applicationId);
}
