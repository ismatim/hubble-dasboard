package hubble.backend.business.services.interfaces.services;

import java.util.List;

public interface ServiceBase<T> {

    public List<T> getLastDay(String applicationId);

    public List<T> getLastMonth(String applicationId);
}
