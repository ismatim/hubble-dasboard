package com.tsoftlatam.tab.frontend.sources.hpbac.services;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Location;
import com.tsoftlatam.tab.frontend.sources.hpbac.repositories.LocationsRepository;
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
}
