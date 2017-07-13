package hubble.frontend.managers.tests;

import hubble.backend.business.domain.AvailabilityBusiness;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import hubble.frontend.managers.interfaces.AvailabilityManager;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class AvailabilityManagerTest {
    
    @Autowired
    private AvailabilityManager appPulseAvailabilityManager;
    
    public AvailabilityManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test    
    public void availability_manager_should_return_one_sample_by_its_id(){
        
        //Assign
        AvailabilityBusiness sample;
        
        //Act
        sample = appPulseAvailabilityManager.findSampleById(4);
        
        //Assert //Verificar cada uno de los campos para saber que la entidad está bien
        assertEquals(4, sample.getSampleId());
    }
    
    @Test
    public void availability_manager_should_return_a_list_of_samples(){
        //Assign
        List<AvailabilityBusiness> samples;
        
        //Act
        samples = appPulseAvailabilityManager.findAllSamples();
        
        //Assert
        assertTrue(!samples.isEmpty());
        
    }
    
    @Test
    public void availability_manager_should_return_just_last_ten_minutes_samples(){
        //Assign
        List<AvailabilityBusiness> samples;
        List<Date> dates = new ArrayList();
        
        //Act
        samples = appPulseAvailabilityManager.findLast10MinutesSamples();
        dates = samples.stream().
                map(AvailabilityBusiness::getTimestamp)
                .collect(toList());
                
        //Assert
        for (Date date : dates) {
            assertTrue(date.after(new Date(Calendar.MINUTE-10)));
        }
        
    }
        
    @Test 
    public void availability_manager_should_return_samples_by_application_id(){
        //Assign
        List<AvailabilityBusiness> samples;
        
        //Act
        samples = appPulseAvailabilityManager.findSamplesByApplicationId(1);
        
        //Assert
        if(!samples.isEmpty()){
            for (AvailabilityBusiness app : samples) {
                assertEquals(1,app.getBusinessApplicationId());
        }
        }
          
            
    }
    
    @Test
    public void availability_manager_should_return_last_10_minutes_samples_by_application_id() {
        //Assign
        List<AvailabilityBusiness> samples;
        List<Date> dates = new ArrayList();

        //Act
        samples = appPulseAvailabilityManager.findLast10MinutesSamplesByApplicationId(1);
        dates = samples.stream().
                map(AvailabilityBusiness::getTimestamp)
                .collect(toList());

        //Assert
        if (!samples.isEmpty()) {
            for (AvailabilityBusiness app : samples) {
                assertEquals(1, app.getBusinessApplicationId());
            }

            for (Date date : dates) {
                assertTrue(date.after(new Date(Calendar.MINUTE-10)));
            }

        }
                     
    }
    
    @Test
    public void availability_manager_should_return_last_hour_samples_by_application_id() {
        //Assign
        List<AvailabilityBusiness> samples;
        List<Date> dates = new ArrayList();

        //Act
        samples = appPulseAvailabilityManager.findLastHourSamplesByApplicationId(1);
        dates = samples.stream().
                map(AvailabilityBusiness::getTimestamp)
                .collect(toList());

        //Assert
        if (!samples.isEmpty()) {
            for (AvailabilityBusiness app : samples) {
                assertEquals(1, app.getBusinessApplicationId());
            }

            for (Date date : dates) {
                assertTrue(date.after(new Date(Calendar.MINUTE-60)));
            }

        }
                     
    }
        
    
    
}
