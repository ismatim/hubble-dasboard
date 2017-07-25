package hubble.backend.tasksrunner.tests.scheduler;

import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.tasksrunner.application.scheduler.SchedulerMediator;
import hubble.backend.tasksrunner.jobs.AppPulseParserJob;
import hubble.backend.tasksrunner.jobs.ParserJob;
import hubble.backend.tasksrunner.tasks.AppPulseTaskImpl;
import hubble.backend.tasksrunner.tasks.ParserTask;
import hubble.backend.tasksrunner.tasks.Task;
import hubble.backend.tasksrunner.tests.configurations.TasksRunnerTestConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import static org.modelmapper.internal.util.Assert.isTrue;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TasksRunnerTestConfiguration.class)
public class SchedulerMediatorUnitTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    static boolean isCalledAppPulseParser = false;
    static int totalExectutedAppPulseParser = 0;

    public SchedulerMediatorUnitTests() {

    }

    @Test
    public void SchedulerMediator_should_save_task_of_apppulseactive_for_later_execution() throws Exception {

        //Assign
        Parser appPulseParser = spy(AppPulseActiveParser.class);
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        ParserJob job = spy(new AppPulseParserJob(appPulseParser));
        Task task = spy(new AppPulseTaskImpl(job));
        task.setIndentityGroupName("AppPulse Active Provider Job");
        task.setIndentityName("AppPulse");
        SchedulerMediator sm = new SchedulerMediator(ctx);
        sm.scheduler = spy(StdSchedulerFactory.getDefaultScheduler());
        task.setIntervalSeconds(10);
        doNothing().when(appPulseParser).run();
        sm.scheduler.deleteJob(new JobKey(task.getIndentityName(), task.getIndentityGroupName()));

        //Act
        sm.addTask(task);

        //Assert
        verify(sm.scheduler).scheduleJob(task.getJobDetail(), task.getTrigger());
    }

    @Test
    public void SchedulerMediator_should_start() throws Exception {

        //Assign
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        SchedulerMediator sm = new SchedulerMediator(ctx);
        sm.scheduler = spy(StdSchedulerFactory.getDefaultScheduler());

        //Act
        sm.start();
        sm.shutdown();

        //Assert
        verify(sm.scheduler).start();
    }

    @Test
    public void SchedulerMediator_should_run_appPulse_job() throws SchedulerException, Exception {

        //Assign
        isCalledAppPulseParser = false;
        Parser appPulseParserFake = spy(AppPulseActiveParser.class);
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        SchedulerMediator schedule = new SchedulerMediator(ctx);

        doAnswer((Answer) (InvocationOnMock invocation) -> {
            return appPulseParserFake;
        }).when(ctx).getBean(AppPulseActiveParser.class);

        doAnswer((Answer) (InvocationOnMock invocation) -> {
            isCalledAppPulseParser = true;
            System.out.println("\nfakeAppPulseParser run.\n");
            return 0;
        }).when(appPulseParserFake).run();

        ParserJob appPulseJob = new AppPulseParserJob(appPulseParserFake);
        ParserTask appPulseTaskFake = new AppPulseTaskImpl(appPulseJob);
        appPulseTaskFake.setIntervalSeconds(1);

        appPulseTaskFake.setIndentityGroupName("AppPulse Active Provider Job");
        appPulseTaskFake.setIndentityName("AppPulse");

        schedule.scheduler.deleteJob(new JobKey(appPulseTaskFake.getIndentityName(), appPulseTaskFake.getIndentityGroupName()));

        schedule.addTask(appPulseTaskFake);

        //Act
        schedule.start();

        Thread.sleep(2000);
        schedule.shutdown();

        //Assert
        isTrue(isCalledAppPulseParser);
    }

    @Test()
    public void ScheduleMediator_should_throw_exception_if_task_doesnt_have_name() throws SchedulerException, Exception {

        //Assign
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        SchedulerMediator schedule = new SchedulerMediator(ctx);
        Parser appPulseParserFake = spy(AppPulseActiveParser.class);
        ParserJob appPulseJob = new AppPulseParserJob(appPulseParserFake);
        ParserTask appPulseTaskFake = new AppPulseTaskImpl(appPulseJob);
        appPulseTaskFake.setIntervalSeconds(1);

        //Act
        exception.expect(Exception.class);
        schedule.addTask(appPulseTaskFake);
    }

    @Test()
    public void ScheduleMediator_should_throw_exception_if_task_doesnt_have_interval() throws SchedulerException, Exception {

        //Assign
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        SchedulerMediator schedule = new SchedulerMediator(ctx);
        Parser appPulseParserFake = spy(AppPulseActiveParser.class);
        ParserJob appPulseJob = new AppPulseParserJob(appPulseParserFake);
        ParserTask appPulseTaskFake = new AppPulseTaskImpl(appPulseJob);
        appPulseTaskFake.setIndentityGroupName("AppPulse Active Provider Job");
        appPulseTaskFake.setIndentityName("AppPulse");

        //Act
        exception.expect(Exception.class);
        schedule.addTask(appPulseTaskFake);
    }

    @Test
    public void SchedulerMeditor_when_job_parser_throws_exception_should_keep_executing() throws Exception {

        //Assign
        isCalledAppPulseParser = false;
        Parser appPulseParserFake = spy(AppPulseActiveParser.class);
        ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
        SchedulerMediator schedule = new SchedulerMediator(ctx);

        doAnswer((Answer) (InvocationOnMock invocation) -> {
            totalExectutedAppPulseParser++;
            return appPulseParserFake;
        }).when(ctx).getBean(AppPulseActiveParser.class);

        doThrow(Exception.class).when(appPulseParserFake).run();

        ParserJob appPulseJob = new AppPulseParserJob(appPulseParserFake);
        ParserTask appPulseTaskFake = new AppPulseTaskImpl(appPulseJob);

        appPulseTaskFake.setIntervalSeconds(1);

        appPulseTaskFake.setIndentityGroupName("AppPulse Active Provider Job");
        appPulseTaskFake.setIndentityName("AppPulse");

        schedule.scheduler.deleteJob(
                new JobKey(appPulseTaskFake.getIndentityName(), appPulseTaskFake.getIndentityGroupName()));

        schedule.addTask(appPulseTaskFake);

        //Act
        schedule.start();

        Thread.sleep(2000);
        schedule.shutdown();

        //Assert
        isTrue(totalExectutedAppPulseParser > 0);
    }

}
