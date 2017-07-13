/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.web.controller;

import hubble.frontend.managers.fake_implementations.ApplicationManagerFakeImpl;
import hubble.frontend.managers.fake_implementations.AvailabilityManagerFakeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import hubble.frontend.managers.interfaces.ApplicationManager;
import hubble.frontend.managers.interfaces.AvailabilityManager;

/**
 *
 * @author alexander.jimenez
 */
@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services"})
public class BasicConfiguration {
 
    @Bean
    public ApplicationManager appPulseApplicationManager() {
        return new ApplicationManagerFakeImpl();
    }
    @Bean
    public AvailabilityManager appPulseAvailabilityManager() {
        return new AvailabilityManagerFakeImpl();
    }
    
}
