package hubble.backend.tasksrunner.jobs;

import hubble.backend.providers.parsers.interfaces.AppPulseActiveParser;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class AppPulseProviderJob implements ProviderJob {

    private AppPulseActiveParser appPulseActiveParser;
    private Logger logger;
    public AppPulseProviderJob() {
        logger = Logger.getLogger(AppPulseProviderJob.class.getName());
    }

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {

        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = (SchedulerContext) jobContext.getScheduler().getContext();
        }
        catch (SchedulerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        ConfigurableApplicationContext taskRunneAppContext =  (ConfigurableApplicationContext) schedulerContext.get("context");
        appPulseActiveParser = taskRunneAppContext.getBean(AppPulseActiveParser.class);

        try {
            appPulseActiveParser.run();
        }
        catch (Exception ex) {
             logger.log(Level.SEVERE, null, ex);
        }
    }
}
