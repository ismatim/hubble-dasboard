package com.tsoftlatam.tab.frontend.sources.hpbac.services;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Application;
import com.tsoftlatam.tab.frontend.sources.hpbac.repositories.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ApplicationsService {

    @Autowired
    private ApplicationsRepository applicationsRepository;

    public Collection<Application> findAllApplications(){
        List<Application> applications = new ArrayList<Application>();
        for (Application application: applicationsRepository.findAll()) {
            applications.add(application);
        }

        return applications;

        }

    public void deleteApplication(int id) {
        applicationsRepository.delete(id);

    }

    public Application findApplicationById(int id){
        return applicationsRepository.findOne(id);
    }

    public void createApplication(Application application){
        applicationsRepository.save(application);
    }

    public Application createApplication(){
        Application application = new Application("Ntest","nTest");
        applicationsRepository.save(application);
        return application;
    }

    public Application updateApplication(int id, String applicationName, String displayName){
        Application application = findApplicationById(id);
        application.setApplicationName(applicationName);
        application.setDisplayName(displayName);
        applicationsRepository.save(application);
        return application;
    }
}
