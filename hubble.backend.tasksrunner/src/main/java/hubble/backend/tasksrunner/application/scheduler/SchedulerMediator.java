package hubble.backend.tasksrunner.application.scheduler;

import hubble.backend.tasksrunner.application.scheduler.menu.Menu;
import hubble.backend.tasksrunner.application.scheduler.menu.MenuImpl;
import hubble.backend.tasksrunner.interfaces.Task;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class SchedulerMediator implements SchedulerUserCommands, SchedulerTasksActions {

    Menu menu;
    Scheduler scheduler;
    ConfigurableApplicationContext context;

    public SchedulerMediator(ConfigurableApplicationContext context, Scheduler scheduler, Menu menu) {
        this.context = context;
        this.scheduler = scheduler;
        this.menu = menu;
    }

    public SchedulerMediator(ConfigurableApplicationContext context) throws SchedulerException, Exception {
        this.context = context;

        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("context", this.context);

        this.menu = new MenuImpl(this);
    }

    @Override
    public ConfigurableApplicationContext getContext() {
        return this.context;
    }

    @Override
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void start() {
        try {
            this.scheduler.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(SchedulerMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pause() {
        try {
            this.scheduler.standby();
        } catch (SchedulerException ex) {
            Logger.getLogger(SchedulerMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void print() {
        try {

            List<String> jobs = scheduler.getJobGroupNames();
            for (String job : jobs) {
                System.out.println(job);
            }

        } catch (SchedulerException ex) {
            Logger.getLogger(SchedulerMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void shutdown() {
        try {
            this.scheduler.shutdown();
        } catch (SchedulerException ex) {
            Logger.getLogger(SchedulerMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addTask(Task taskRunner) {
        try {
            this.scheduler.scheduleJob(taskRunner.getJobDetail(), taskRunner.getTrigger());
        } catch (SchedulerException ex) {
            Logger.getLogger(SchedulerMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void showMenu() {
        menu.execute();
    }
}
