package hubble.backend.business.services.interfaces.operations;

public interface AverageOperationsBase<T> {

    public T calculateLast10MinutesAverageByApplication(String applicationId);

    public T calculateLastHourAverageByApplication(String applicationId);

    public T calculateLastDayAverageByApplication(String applicationId);

    public T calculateLastMonthAverageByApplication(String applicationId);
}
