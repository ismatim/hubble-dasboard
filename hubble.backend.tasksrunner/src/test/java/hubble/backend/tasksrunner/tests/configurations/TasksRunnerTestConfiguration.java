package hubble.backend.tasksrunner.tests.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan(basePackages = {"hubble.backend.tasksrunner", "hubble.backend.providers", "hubble.backend.storage"})
public class TasksRunnerTestConfiguration {

}
