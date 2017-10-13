package hubble.backend.tasksrunner.jobs.bsm;

import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.providers.parsers.interfaces.bsm.BsmApplicationParser;
import hubble.backend.tasksrunner.jobs.ParserJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ConfigurableApplicationContext;

public class BsmApplicationParserJob implements ParserJob {

    private Parser bsmParser;
    private static final Logger logger = Logger.getLogger(BsmApplicationParserJob.class.getName());

    public BsmApplicationParserJob() {
        //This constructor is used by Quartz. DON'T DELETE. CANT SET DEFAULT CONSTRUCTOR.
    }

    public BsmApplicationParserJob(Parser parser) {
        bsmParser = parser;
    }

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {

        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = (SchedulerContext) jobContext.getScheduler().getContext();
        } catch (SchedulerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        ConfigurableApplicationContext taskRunneAppContext = (ConfigurableApplicationContext) schedulerContext.get("context");
        bsmParser = taskRunneAppContext.getBean(BsmApplicationParser.class);

        try {
            bsmParser.run();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Parser getParser() {
        return bsmParser;
    }

    @Override
    public void setParser(Parser parser) {
        bsmParser = parser;
    }
}
