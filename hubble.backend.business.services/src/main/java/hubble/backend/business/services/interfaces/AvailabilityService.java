package hubble.backend.business.services.interfaces;

import hubble.backend.business.services.models.AvailabilityDto;

import java.util.List;

public interface AvailabilityService {

    public List<AvailabilityDto> findAllAvailabilities();

    public AvailabilityDto findAvailabilityById(String id);

    public List<AvailabilityDto> findAvailabilitiesByApplicationId(String applicationId);

    public List<AvailabilityDto> findLast10MinutesAvailabilities();

    public List<AvailabilityDto> findLastHourAvailabilities();

    public List<AvailabilityDto> findLast10MinutesAvailabilitiesByApplicationId(String id);

    public List<AvailabilityDto> findLastHourAvailabilitiesByApplicationId(String id);

}
