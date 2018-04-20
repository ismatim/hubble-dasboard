package hubble.backend.business.services.interfaces.services.kpis;

/**
 * Offers the standard operation for unit of measures.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Model to measure.
 */
public interface InstantOperationsKeyPerformanceIndicatorServiceBase<T> {

    public T calculateLast10MinutesKpiByApplication(String applicationId);

    public T calculateLastHourKpiByApplication(String applicationId);
}
