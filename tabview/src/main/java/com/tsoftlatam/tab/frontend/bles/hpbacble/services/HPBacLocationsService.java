package com.tsoftlatam.tab.frontend.bles.hpbacble.services;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacLocation;
import com.tsoftlatam.tab.frontend.bles.hpbacble.repositories.HPBacLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HPBacLocationsService {

    @Autowired
    private HPBacLocationsRepository hpBacLocationsRepository;

    public Collection<HPBacLocation> findAllLocations(){
        List<HPBacLocation> locations = new ArrayList<HPBacLocation>();
        for (HPBacLocation location: hpBacLocationsRepository.findAll()) {
            locations.add(location);
        }

        return locations;

    }

    public void deleteLocation(int id) {
        hpBacLocationsRepository.delete(id);

    }

    public HPBacLocation findLocationById(int id){
        return hpBacLocationsRepository.findOne(id);
    }
}
