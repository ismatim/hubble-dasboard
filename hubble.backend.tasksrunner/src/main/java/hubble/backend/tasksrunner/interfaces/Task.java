package hubble.backend.tasksrunner.interfaces;
import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface Task {
    public Trigger getTrigger();
    public JobDetail getJobDetail();
}
