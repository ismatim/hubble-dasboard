package hubble.frontend.repositories;

import hubble.frontend.models.Threshold;
import org.springframework.data.repository.CrudRepository;

public interface ThresholdsRepository extends CrudRepository<Threshold, Integer> {
}
