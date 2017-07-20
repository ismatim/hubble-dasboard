package hubble.backend.tasksrunner.implementations;

import hubble.backend.tasksrunner.interfaces.Task;
import hubble.backend.tasksrunner.jobs.AppPulseProviderJob;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;

public class AppPulseTaskImpl implements Task {

    @Override
    public Trigger getTrigger() {
        Trigger trigger = newTrigger()
                .withIdentity("AppPulse Trigger", "AppPulse Group")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();
        return trigger;
    }

    @Override
    public JobDetail getJobDetail() {
        JobDetail job = newJob(AppPulseProviderJob.class)
                .withIdentity("AppPulse", "AppPulse Active Provider Job")
                .build();
        return job;
    }
}
