package hubble.frontend.managers.tests;

import hubble.frontend.managers.interfaces.AvailabilityManager;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class AvailabilityManagerUnitTest {

    @Autowired
    private AvailabilityManager appPulseAvailabilityManager;

    public AvailabilityManagerUnitTest() {
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
    public void availability_manager_should_return_one_sample_by_its_id() {

    }
    /*
    @Test
    public void availability_manager_should_return_a_list_of_samples(){
        //Assign
        List<AvailabilityBusiness> samples;

        //Act
        samples = appPulseAvailabilityManager.findAllAvailabilities();

        //Assert
        assertTrue(!samples.isEmpty());

    }

    @Test
    public void availability_manager_should_return_just_last_ten_minutes_samples(){
        //Assign
        List<AvailabilityBusiness> samples;
        List<Date> dates = new ArrayList();

        //Act
        samples = appPulseAvailabilityManager.findLast10MinutesAvailabilities();
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
        samples = appPulseAvailabilityManager.findAvailabilitiesByApplicationId(1);

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
        samples = appPulseAvailabilityManager.findLast10MinutesAvailabilitiesByApplicationId(1);
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
        samples = appPulseAvailabilityManager.findLastHourAvailabilitiesByApplicationId(1);
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

     */

}
