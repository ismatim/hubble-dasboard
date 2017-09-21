package hubble.backend.tasksrunner.tests.scheduler;

import hubble.backend.providers.parsers.interfaces.alm.AlmDataParser;
import hubble.backend.providers.parsers.interfaces.ppm.PpmApplicationParser;
import hubble.backend.providers.parsers.interfaces.ppm.PpmDataParser;
import hubble.backend.tasksrunner.application.scheduler.SchedulerMediator;
import hubble.backend.tasksrunner.jobs.ParserJob;
import hubble.backend.tasksrunner.jobs.alm.AlmDataParserJob;
import hubble.backend.tasksrunner.jobs.ppm.PpmApplicationParserJob;
import hubble.backend.tasksrunner.jobs.ppm.PpmDataParserJob;
import hubble.backend.tasksrunner.tasks.Task;
import hubble.backend.tasksrunner.tasks.alm.AlmDataTaskImpl;
import hubble.backend.tasksrunner.tasks.ppm.PpmApplicationTaskImpl;
import hubble.backend.tasksrunner.tasks.ppm.PpmDataTaskImpl;
import hubble.backend.tasksrunner.tests.configurations.TasksRunnerTestConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class PpmTaskIntegrationTests {
    
    @Autowired
    private ApplicationContext appContext;
    
    public PpmTaskIntegrationTests() {
    }
    
    @Test
    public void SchedulerMediator_should_schedule_ppm_job() throws SchedulerException, Exception {

        //Assign
        PpmDataParser ppmParser = appContext.getBean(PpmDataParser.class);
        SchedulerMediator schedule = new SchedulerMediator((ConfigurableApplicationContext) appContext);

        ParserJob ppmDataJob = new PpmDataParserJob(ppmParser);
        Task ppmDataTask = new PpmDataTaskImpl(ppmDataJob);
        ppmDataTask.setIndentityGroupName("Ppm Provider Job");
        ppmDataTask.setIndentityName("Ppm Data");
        ppmDataTask.setIntervalSeconds(1);
        schedule.addTask(ppmDataTask);
        //Act
        schedule.start();
        Thread.sleep(4000);

        //Assert
        schedule.shutdown();
    }
    
    @Test
    public void SchedulerMediator_should_schedule_ppm_application_job() throws SchedulerException, Exception {

        //Assign
        PpmApplicationParser ppmParser = appContext.getBean(PpmApplicationParser.class);
        SchedulerMediator schedule = new SchedulerMediator((ConfigurableApplicationContext) appContext);

        ParserJob ppmApplicationJob = new PpmApplicationParserJob(ppmParser);
        Task ppmApplicationTask = new PpmApplicationTaskImpl(ppmApplicationJob);
        ppmApplicationTask.setIndentityGroupName("Ppm Provider Job");
        ppmApplicationTask.setIndentityName("Ppm Application");
        ppmApplicationTask.setIntervalSeconds(1);
        schedule.addTask(ppmApplicationTask);
        //Act
        schedule.start();
        Thread.sleep(4000);

        //Assert
        schedule.shutdown();
    }
    
}
