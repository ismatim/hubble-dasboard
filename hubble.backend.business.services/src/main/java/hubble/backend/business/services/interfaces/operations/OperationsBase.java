package hubble.backend.business.services.interfaces.operations;

public interface OperationsBase<T> {

    public T calculateLast10MinutesAverageByApplication(String applicationId);

    public T calculateLastHourAverageByApplication(String applicationId);

    public T calculateLastDayAverageByApplication(String applicationId);

    public T calculateLastMonthAverageByApplication(String applicationId);
}
