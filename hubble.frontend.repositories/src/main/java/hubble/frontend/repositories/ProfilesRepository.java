package hubble.frontend.repositories;


import hubble.frontend.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfilesRepository extends CrudRepository<Profile, Integer> {
}
