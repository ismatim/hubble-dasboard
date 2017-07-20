package hubble.backend.tasksrunner.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan(basePackages = {"hubble.backend.providers", "hubble.backend.storage", "hubble.backend.tasksrunner", "hubble.backend.core.utils"})
public class TasksRunnerConfiguration {

}
