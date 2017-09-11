package hubble.backend.tasksrunner.jobs.alm;

import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.providers.parsers.interfaces.alm.AlmApplicationParser;
import hubble.backend.providers.parsers.interfaces.alm.AlmDataParser;
import hubble.backend.tasksrunner.jobs.ParserJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ConfigurableApplicationContext;

public class AlmApplicationParserJob implements ParserJob{
    
    private Parser almParser;
    private static final Logger logger = Logger.getLogger(AlmApplicationParserJob.class.getName());

    public AlmApplicationParserJob() {
        //This constructor is used by Quartz. DON'T DELETE. CANT SET DEFAULT CONSTRUCTOR.
    }

    public AlmApplicationParserJob(Parser almParser) {
        this.almParser = almParser;
    }
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = (SchedulerContext) jec.getScheduler().getContext();
        } catch (SchedulerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        ConfigurableApplicationContext taskRunnerAppContext = (ConfigurableApplicationContext) schedulerContext.get("context");
        almParser = taskRunnerAppContext.getBean(AlmApplicationParser.class);

        try {
            almParser.run();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Parser getParser() {
        return this.almParser;
    }

    @Override
    public void setParser(Parser parser) {
        this.almParser = parser;
    } 
}
