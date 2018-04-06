package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.Application;
import java.util.List;

public interface ApplicationServiceBase<T> extends ServiceBase<T> {

    public Application getApplication(String applicationId);

    public List<Application> getAllApplications();

    public List<T> getAll(String applicationId);

    public List<T> getLast10Minutes(String applicationId);

    public List<T> getLastHour(String applicationId);

}
