package hubble.frontend.services;

import hubble.frontend.models.Location;
import hubble.frontend.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LocationsService {

    @Autowired
    private LocationsRepository locationsRepository;

    public Collection<Location> findAllLocations(){
        List<Location> locations = new ArrayList<Location>();
        for (Location location: locationsRepository.findAll()) {
            locations.add(location);
        }

        return locations;

    }

    public void deleteLocation(int id) {
        locationsRepository.delete(id);

    }

    public Location findLocationById(int id){
        return locationsRepository.findOne(id);
    }

    public void createLocation(Location location){
        locationsRepository.save(location);
    }

    public Location updateLocation(int id, String locationName, String displayName, Boolean isInternal){
        Location location = findLocationById(id);
        location.setIsInternal(isInternal);
        location.setLocationName(locationName);
        location.setDisplayName(displayName);
        locationsRepository.save(location);
        return location;
    }

    public String findLocationDisplayName(String name){
        Collection<Location> allLocations = findAllLocations();
        Location location;

        location = allLocations.parallelStream()
                .filter(x -> name.equals(x.getLocationName()))        // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(location!=null)
            return location.getDisplayName();
        else
            return null;

    }

    public int findLocationIdByName(String name){

        Collection<Location> allLocations = findAllLocations();
        Location location;

        location = allLocations.parallelStream()
                .filter(x -> name.equals(x.getLocationName()))   // Buscamos por nombre
                .findAny()                                      // Si encuentra uno devuelve el objeto encontrado
                .orElse(null);                          //Si no encuentra nada devuelve null

        if(location!=null)
            return location.getId();
        else
            return -1;
    }
}
