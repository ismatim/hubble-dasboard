package com.tsoftlatam.tab.frontend.bles.hpbacble.services;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacApplication;
import com.tsoftlatam.tab.frontend.bles.hpbacble.repositories.HPBacApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HPBacApplicationsService {

    @Autowired
    private HPBacApplicationsRepository hpBacApplicationsRepository;

    public Collection<HPBacApplication> findAllApplications(){
        List<HPBacApplication> applications = new ArrayList<HPBacApplication>();
        for (HPBacApplication application:hpBacApplicationsRepository.findAll()) {
            applications.add(application);
        }

        return applications;

        }

    public void deleteApplication(int id) {
        hpBacApplicationsRepository.delete(id);

    }

    public HPBacApplication findApplicationById(int id){
        return hpBacApplicationsRepository.findOne(id);
    }

    public void createApplication(HPBacApplication hpBacApplication){
        hpBacApplicationsRepository.save(hpBacApplication);
    }

    public void createApplication(){
        HPBacApplication hpBacApplication = new HPBacApplication("Ntest","nTest");
        hpBacApplicationsRepository.save(hpBacApplication);
    }
}
