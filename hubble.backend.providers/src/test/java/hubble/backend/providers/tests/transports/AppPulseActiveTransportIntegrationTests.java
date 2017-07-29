package hubble.backend.providers.tests.transports;

import hubble.backend.providers.tests.configurations.BaseConfiguration;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class AppPulseActiveTransportIntegrationTests {

    @Autowired
    private AppPulseActiveTransport appPulseActiveTransport;

    @Test
    public void AppPulseActiveTransport_should_be_instantiated() {
        assertNotNull(appPulseActiveTransport);
    }

    @Test
    public void AppPulseActiveTransport_should_get_token() {

        //Act
        String token = appPulseActiveTransport.getToken();

        //Assert
        assertFalse(EMPTY.equals(token));

    }

    @Test
    public void AppPulseActiveTransport_should_get_data() {

        //Act
        JSONObject data = appPulseActiveTransport.getData();

        //Assert
        assertNotNull(data);
        assertTrue(data.length() > 0);
    }

    @Test
    public void AppPulseActiveTransport_should_get_same_json_stucture() {
        //Act
        JSONObject data = appPulseActiveTransport.getData();

        //Assert
        assertNotNull(data);
        assertTrue(data.length() > 0);
        assertTrue(data.has("lastRetrievedSequenceId"));
        assertTrue(data.has("hasMoreDataToFetch"));
        assertTrue(data.has("data"));
    }

    @Test
    public void AppPulseActiveTransport_should_get_applications() {
        //Act
        JSONObject data = appPulseActiveTransport.getApplications();

        //Assert
        assertNotNull(data);
        assertTrue(data.length() > 0);
    }
}
