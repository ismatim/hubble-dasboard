package hubble.backend.providers.tests.parsers.apppulse;

import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.providers.tests.configurations.BaseConfiguration;
import hubble.backend.providers.transports.implementations.apppulse.AppPulseActiveTransportImpl;
import org.json.JSONObject;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
@ActiveProfiles("test")
public class AppPulseActiveTransportIntegrationTest extends AppPulseBaseUnitTests {

    @Autowired
    private AppPulseActiveTransportImpl appPulseActiveTransport;

    @Test
    public void AppPulseActiveTransportImpl_should_be_instantiated() {
        assertNotNull(appPulseActiveTransport);
    }

    @Test
    public void AppPulseActiveTransportImpl_when_it_runs_should_connect_get_data_and_save_it() {

        //Act
        appPulseActiveTransport.getToken();
        JSONObject data = appPulseActiveTransport.getData();

        //Assert
        assertNotNull(data);
        assertTrue(data.has("hasMoreDataToFetch"));
        assertTrue(data.has("lastRetrievedSequenceId"));
        assertTrue(data.has("data"));
    }
}
