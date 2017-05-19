package com.tsoftlatam.tab.frontend.bles.hpbacble.services;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacThreshold;
import com.tsoftlatam.tab.frontend.bles.hpbacble.repositories.HPBacThresholdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HPBacThresholdsService {

    @Autowired
    HPBacThresholdsRepository hpBacThresholdsRepository;

    public Collection<HPBacThreshold> findAllThresholds(){
        List<HPBacThreshold> thresholds = new ArrayList<HPBacThreshold>();
        for (HPBacThreshold threshold: hpBacThresholdsRepository.findAll()) {
            thresholds.add(threshold);
        }

        return thresholds;

    }

    public void deleteThreshold(int id) {
        hpBacThresholdsRepository.delete(id);

    }

    public HPBacThreshold findThresholdById(int id){
        return hpBacThresholdsRepository.findOne(id);
    }
}
