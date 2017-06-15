package hubble.frontend.services;

import hubble.frontend.models.Threshold;
import hubble.frontend.repositories.ThresholdsRepository;
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

    public Threshold updateThreshold(int id, float okThreshold, float criticalThreshold, float outlierThreshold){
        Threshold threshold = findThresholdById(id);
        threshold.setOkThreshold(okThreshold);
        threshold.setCriticalThreshold(criticalThreshold);
        threshold.setOutlierThreshold(outlierThreshold);

        thresholdsRepository.save(threshold);
        return threshold;
    }

    public void createThreshold(Threshold threshold){
        thresholdsRepository.save(threshold);
    }
}
