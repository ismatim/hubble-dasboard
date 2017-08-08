package hubble.backend.providers.tests.transports;

import hubble.backend.providers.configurations.ProvidersConfiguration;
import hubble.backend.providers.transports.interfaces.AppPulseActiveTransport;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.json.JSONObject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProvidersConfiguration.class)
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
