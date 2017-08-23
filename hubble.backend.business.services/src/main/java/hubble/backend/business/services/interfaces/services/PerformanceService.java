package hubble.backend.business.services.interfaces.services;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.PerformanceDto;
import java.util.List;

public interface PerformanceService extends OperationsServiceBase<ApplicationAvgDto>, ApplicationServiceBase<PerformanceDto> {

    public List<PerformanceDto> getAll();

    public PerformanceDto getById(String id);

    public List<PerformanceDto> getLast10Minutes();

    public List<PerformanceDto> getLastHour();

    public List<PerformanceDto> getLastDay();

    public List<PerformanceDto> getLastMonth();

}
