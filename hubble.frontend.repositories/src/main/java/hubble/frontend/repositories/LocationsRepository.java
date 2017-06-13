package hubble.frontend.repositories;

import hubble.frontend.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationsRepository extends CrudRepository<Location, Integer> {
}
