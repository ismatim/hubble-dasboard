package com.tsoftlatam.tab.frontend.sources.hpbac.services;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Threshold;
import com.tsoftlatam.tab.frontend.sources.hpbac.repositories.ThresholdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ThresholdsService {

    @Autowired
    ThresholdsRepository thresholdsRepository;

    public Collection<Threshold> findAllThresholds(){
        List<Threshold> thresholds = new ArrayList<Threshold>();
        for (Threshold threshold: thresholdsRepository.findAll()) {
            thresholds.add(threshold);
        }

        return thresholds;

    }

    public void deleteThreshold(int id) {
        thresholdsRepository.delete(id);

    }

    public Threshold findThresholdById(int id){
        return thresholdsRepository.findOne(id);
    }
}
