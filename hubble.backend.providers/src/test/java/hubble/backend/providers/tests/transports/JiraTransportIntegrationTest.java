package hubble.backend.providers.tests.transports;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hubble.backend.providers.configurations.ProvidersConfiguration;
import hubble.backend.providers.transports.interfaces.JiraTransport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProvidersConfiguration.class)
public class JiraTransportIntegrationTest {
	
	@Autowired
	private JiraTransport jiraTransport;
}
