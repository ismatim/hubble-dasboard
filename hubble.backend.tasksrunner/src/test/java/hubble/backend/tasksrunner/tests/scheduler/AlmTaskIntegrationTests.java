package hubble.backend.tasksrunner.tests.scheduler;

import hubble.backend.providers.parsers.interfaces.alm.AlmApplicationParser;
import hubble.backend.providers.parsers.interfaces.alm.AlmDataParser;
import hubble.backend.tasksrunner.application.scheduler.SchedulerMediator;
import hubble.backend.tasksrunner.jobs.ParserJob;
import hubble.backend.tasksrunner.jobs.alm.AlmApplicationParserJob;
import hubble.backend.tasksrunner.jobs.alm.AlmDataParserJob;
import hubble.backend.tasksrunner.tasks.Task;
import hubble.backend.tasksrunner.tasks.alm.AlmApplicationTaskImpl;
import hubble.backend.tasksrunner.tasks.alm.AlmDataTaskImpl;
import hubble.backend.tasksrunner.tests.configurations.TasksRunnerTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = TasksRunnerTestConfiguration.class)
public class AlmTaskIntegrationTests {
    
    @Autowired
    private ApplicationContext appContext;

    public AlmTaskIntegrationTests() {
    }
    
    @Test
    public void SchedulerMediator_should_schedule_alm_job() throws SchedulerException, Exception {

        //Assign
        AlmDataParser almParser = appContext.getBean(AlmDataParser.class);
        SchedulerMediator schedule = new SchedulerMediator((ConfigurableApplicationContext) appContext);

        ParserJob almDataJob = new AlmDataParserJob(almParser);
        Task almDataTask = new AlmDataTaskImpl(almDataJob);
        almDataTask.setIndentityGroupName("Alm Provider Job");
        almDataTask.setIndentityName("Alm Data");
        almDataTask.setIntervalSeconds(1);
        schedule.addTask(almDataTask);
        //Act
        schedule.start();
        Thread.sleep(4000);

        //Assert
        schedule.shutdown();
    }
    
    @Test
    public void SchedulerMediator_should_schedule_alm_applications_job() throws SchedulerException, Exception {

        //Assign
        AlmApplicationParser almParser = appContext.getBean(AlmApplicationParser.class);
        SchedulerMediator schedule = new SchedulerMediator((ConfigurableApplicationContext) appContext);

        ParserJob almApplicationJob = new AlmApplicationParserJob(almParser);
        Task almApplicationTask = new AlmApplicationTaskImpl(almApplicationJob);
        almApplicationTask.setIndentityGroupName("Alm Provider Job");
        almApplicationTask.setIndentityName("Alm Applications");
        almApplicationTask.setIntervalSeconds(1);
        schedule.addTask(almApplicationTask);
        //Act
        schedule.start();
        Thread.sleep(4000);

        //Assert
        schedule.shutdown();
    }
    
}
