/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.frontend.web.controller;

import hubble.frontend.managers.fake_implementations.AppPulseApplicationManagerFakeImpl;
import hubble.frontend.managers.fake_implementations.AppPulseAvailabilityManagerFakeImpl;
import hubble.frontend.managers.interfaces.AppPulseApplicationManager;
import hubble.frontend.managers.interfaces.AppPulseAvailabilityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author alexander.jimenez
 */
@Configuration
@ComponentScan(basePackages = {"hubble.backend.business.services"})
public class BasicConfiguration {
 
    @Bean
    public AppPulseApplicationManager appPulseApplicationManager() {
        return new AppPulseApplicationManagerFakeImpl();
    }
    @Bean
    public AppPulseAvailabilityManager appPulseAvailabilityManager() {
        return new AppPulseAvailabilityManagerFakeImpl();
    }
    
}
