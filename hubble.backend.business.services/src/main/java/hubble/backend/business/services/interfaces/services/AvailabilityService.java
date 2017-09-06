package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import java.util.List;

public interface AvailabilityService extends OperationsServiceBase<ApplicationAvgDto>, ApplicationServiceBase<AvailabilityDto> {

    public List<AvailabilityDto> getAll();

    public AvailabilityDto get(String id);

    public List<AvailabilityDto> getLast10Minutes();

    public List<AvailabilityDto> getLastHour();

    public List<AvailabilityDto> getLastDay();

    public List<AvailabilityDto> getLastMonth();

}
