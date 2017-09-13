package hubble.backend.tasksrunner.application;

import hubble.backend.providers.parsers.interfaces.alm.AlmApplicationParser;
import hubble.backend.providers.parsers.interfaces.alm.AlmDataParser;
import hubble.backend.providers.parsers.interfaces.apppulse.AppPulseActiveApplicationsParser;
import hubble.backend.providers.parsers.interfaces.apppulse.AppPulseActiveDataParser;
import hubble.backend.providers.parsers.interfaces.bsm.BsmApplicationParser;
import hubble.backend.providers.parsers.interfaces.bsm.BsmDataParser;
import hubble.backend.tasksrunner.application.scheduler.SchedulerMediator;
import hubble.backend.tasksrunner.configurations.TasksRunnerConfiguration;
import hubble.backend.tasksrunner.jobs.ParserJob;
import hubble.backend.tasksrunner.jobs.alm.AlmApplicationParserJob;
import hubble.backend.tasksrunner.jobs.alm.AlmDataParserJob;
import hubble.backend.tasksrunner.jobs.apppulse.AppPulseApplicationParserJob;
import hubble.backend.tasksrunner.jobs.apppulse.AppPulseDataParserJob;
import hubble.backend.tasksrunner.jobs.bsm.BsmApplicationParserJob;
import hubble.backend.tasksrunner.jobs.bsm.BsmDataParserJob;
import hubble.backend.tasksrunner.tasks.ParserTask;
import hubble.backend.tasksrunner.tasks.Task;
import hubble.backend.tasksrunner.tasks.alm.AlmApplicationTaskImpl;
import hubble.backend.tasksrunner.tasks.alm.AlmDataTaskImpl;
import hubble.backend.tasksrunner.tasks.apppulse.AppPulseApplicationTaskImpl;
import hubble.backend.tasksrunner.tasks.apppulse.AppPulseDataTaskImpl;
import hubble.backend.tasksrunner.tasks.bsm.BsmApplicationTaskImpl;
import hubble.backend.tasksrunner.tasks.bsm.BsmDataTaskImpl;
import org.quartz.SchedulerException;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class TasksRunnerApplication {

    SchedulerMediator scheduler;
    ConfigurableApplicationContext context;

    public void run(ConfigurableApplicationContext context) throws SchedulerException, Exception {

        scheduler = new SchedulerMediator(context);

        //AppPulse
        AppPulseActiveDataParser appPulseparser = context.getBean(AppPulseActiveDataParser.class);
        ParserJob appPulseJob = new AppPulseDataParserJob(appPulseparser);
        ParserTask appPulseDataTask = new AppPulseDataTaskImpl(appPulseJob);
        appPulseDataTask.setIndentityGroupName("AppPulse Active Provider Job");
        appPulseDataTask.setIndentityName("AppPulse Data");
        appPulseDataTask.setIntervalSeconds(40);

        AppPulseActiveApplicationsParser appPulseApplicationParser = context.getBean(AppPulseActiveApplicationsParser.class);
        ParserJob appPulseAppJob = new AppPulseApplicationParserJob(appPulseApplicationParser);
        ParserTask appPulseApplicationTask = new AppPulseApplicationTaskImpl(appPulseAppJob);
        appPulseApplicationTask.setIndentityGroupName("AppPulse Active Provider Job");
        appPulseApplicationTask.setIndentityName("AppPulse Applications");
        appPulseApplicationTask.setIntervalSeconds(100);
        scheduler.addTask(appPulseApplicationTask);
        scheduler.addTask(appPulseDataTask);

        //BSM
        BsmDataParser bsmParser = context.getBean(BsmDataParser.class);
        ParserJob bsmJob = new BsmDataParserJob(bsmParser);
        ParserTask bsmTask = new BsmDataTaskImpl(bsmJob);
        bsmTask.setIndentityGroupName("BSM");
        bsmTask.setIndentityName("BSM Data Transacciones");
        bsmTask.setIntervalSeconds(40);

        BsmApplicationParser bsmApplicationParser = context.getBean(BsmApplicationParser.class);
        ParserJob bsmApplicationJob = new BsmApplicationParserJob(bsmApplicationParser);
        Task bsmApplicationTask = new BsmApplicationTaskImpl(bsmApplicationJob);
        bsmApplicationTask.setIndentityGroupName("BSM");
        bsmApplicationTask.setIndentityName("BSM Applicaciones");
        bsmApplicationTask.setIntervalSeconds(100);
        scheduler.addTask(bsmApplicationTask);
        scheduler.addTask(bsmTask);

        //Alm
        AlmDataParser almParser = context.getBean(AlmDataParser.class);
        ParserJob almJob = new AlmDataParserJob(almParser);
        ParserTask almDataTask = new AlmDataTaskImpl(almJob);
        almDataTask.setIndentityGroupName("Alm Provider Job");
        almDataTask.setIndentityName("Alm Data");
        almDataTask.setIntervalSeconds(40);

        AlmApplicationParser almApplicationParser = context.getBean(AlmApplicationParser.class);
        ParserJob almApplicationJob = new AlmApplicationParserJob(almApplicationParser);
        ParserTask almApplicationTask = new AlmApplicationTaskImpl(almApplicationJob);
        almApplicationTask.setIndentityGroupName("Alm Provider Job");
        almApplicationTask.setIndentityName("Alm Applications");
        almApplicationTask.setIntervalSeconds(100);

        scheduler.addTask(almDataTask);
        scheduler.addTask(almApplicationTask);

        scheduler.showMenu();
    }

    public static void main(String[] args) throws Exception {

        //TODO: profile should be set as parameter.
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .profiles("test")
                .sources(TasksRunnerConfiguration.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

        TasksRunnerApplication app = context.getBean(TasksRunnerApplication.class);
        app.run(context);
    }
}
