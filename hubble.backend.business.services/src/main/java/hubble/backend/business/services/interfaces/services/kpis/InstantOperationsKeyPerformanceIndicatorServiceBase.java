package hubble.backend.business.services.interfaces.services.kpis;

/**
 * Offers the standard operation for a unit of measure.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Model to measure.
 */
public interface InstantOperationsKeyPerformanceIndicatorServiceBase<T> {

    public T calculateLast10MinutesKpiByApplication(String applicationId);

    public T calculateLastHourKpiByApplication(String applicationId);
}
