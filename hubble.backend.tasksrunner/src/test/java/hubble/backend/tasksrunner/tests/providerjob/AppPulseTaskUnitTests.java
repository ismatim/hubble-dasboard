package hubble.backend.tasksrunner.tests.providerjob;

import hubble.backend.tasksrunner.tests.configurations.TasksRunnerTestConfiguration;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TasksRunnerTestConfiguration.class)
public class AppPulseTaskUnitTests {

    public AppPulseTaskUnitTests() {

    }
}
