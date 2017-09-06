package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.ApplicationDto;
import java.util.List;

public interface ApplicationServiceBase<T> {

    public ApplicationDto getApplication(String applicationId);

    public List<ApplicationDto> getAllApplications();

    public List<T> getAll(String applicationId);

    public List<T> getLast10Minutes(String applicationId);

    public List<T> getLastHour(String applicationId);

    public List<T> getLastDay(String applicationId);

    public List<T> getLastMonth(String applicationId);

}
