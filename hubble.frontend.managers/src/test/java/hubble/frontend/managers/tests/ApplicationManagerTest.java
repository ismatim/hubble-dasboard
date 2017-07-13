package hubble.frontend.managers.tests;

import hubble.frontend.managers.models.BusinessApplication;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import hubble.frontend.managers.interfaces.ApplicationManager;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class ApplicationManagerTest {
   
    @Autowired
    private ApplicationManager manager;
    
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
    public void application_manager_should_return_all_applications(){
        //Assign
        List<BusinessApplication> applications = new ArrayList();
        int x=0;
        
        //Act
        applications = manager.findAllApplications();
        
        //Assert
        for (BusinessApplication app : applications) {
            x++;
            assertEquals(x, app.getId());            
        }
    }
    
    @Test
    public void application_manager_should_return_one_application_by_its_id(){
        //Assign
        BusinessApplication application;
        int id=1;
        
        //Act
        application = manager.findBusinessApplicationById(id);
        
        //Assert
        assertEquals(1, application.getId());            
    }
    
}
