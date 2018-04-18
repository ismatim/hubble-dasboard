package hubble.backend.business.services.interfaces.services.kpis;

/**
 * Offers the standard operation for a unit of measure.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Model to measure.
 */
public interface OperationsKeyPerformanceIndicatorServiceBase<T> {

    public T calculateLastDayKpiByApplication(String applicationId);

    public T calculateLastMonthKpiByApplication(String applicationId);
}
