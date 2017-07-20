package hubble.backend.tasksrunner.tests.providerjob;

import hubble.backend.tasksrunner.application.scheduler.SchedulerMediator;
import hubble.backend.tasksrunner.implementations.AppPulseTaskImpl;
import hubble.backend.tasksrunner.tests.configurations.TasksRunnerTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TasksRunnerTestConfiguration.class)
public class AppPulseTaskIntegrationTests {

    @Autowired
    private ApplicationContext appContext;

    public AppPulseTaskIntegrationTests() {
    }

    @Test
    public void AppPulseTask_schedule_apppulse_provider_job() throws SchedulerException, Exception {

        //Assign
        SchedulerMediator schedule = new SchedulerMediator((ConfigurableApplicationContext) appContext);
        AppPulseTaskImpl task = new AppPulseTaskImpl();

        schedule.addTask(task);
        //Act
        schedule.start();
        Thread.sleep(4000);

        //Assert
        schedule.shutdown();
    }
}
