package hubble.backend.tasksrunner.application.scheduler;

import hubble.backend.tasksrunner.tasks.Task;
import org.springframework.context.ConfigurableApplicationContext;

public interface SchedulerTasksActions {

    public ConfigurableApplicationContext getContext();

    public void setContext(ConfigurableApplicationContext context);

    public void addTask(Task taskRunner) throws Exception;
}
