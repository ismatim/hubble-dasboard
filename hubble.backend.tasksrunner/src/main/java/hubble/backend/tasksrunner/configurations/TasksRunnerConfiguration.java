package hubble.backend.tasksrunner.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hubble.backend.providers", "hubble.backend.storage", "hubble.backend.tasksrunner", "hubble.backend.core"})
public class TasksRunnerConfiguration {

}
